#Based off of SentDex's code
import numpy as np
import random
from PIL import ImageGrab
import cv2
import time
from numpy import ones,vstack
from numpy.linalg import lstsq
from directkeys import PressKey, ReleaseKey, W, A, S, D
from statistics import mean
from getkeys import key_check
import os

# READ ANY KEYS PRESSED
def keys_to_output(keys):
    # possible keys: [A, W, D]
    # A = turn left
    # W = go straight
    # D = turn right
    output = [0,0,0]

    if 'A' in keys:
        output[0] = 1
    elif 'W' in keys:
        output[1] = 1
    else:
        output[2] = 1

    return output

# Create a numpy file
file_name = 'training_data.npy'

if os.path.isfile(file_name):
    print('File exists.  Loading previous data...')
    training_data = list(np.load(file_name))
else:
    print('File does not exist.  Starting fresh...') 
    training_data = []





# REGION OF INTEREST
# cuts a region of the screen to reduce computation time; this screen is meant to hold driving lanes
def roi(img, vertices):
    
    # blank mask:
    mask = np.zeros_like(img)   
    
    # filling pixels inside the polygon defined by "vertices" with the fill color    
    cv2.fillPoly(mask, vertices, 255)
    
    # returning the image only where mask pixels are nonzero
    masked = cv2.bitwise_and(img, mask)
    return masked

# DRAW LANES
# uses hough lines to draw lanes meant to represent driving lanes
def draw_lanes(img, lines, color=[0, 255, 255], thickness=3):

    # if this fails, go with some default line
    try:

        # finds the maximum y value for a lane marker 
        # (since we cannot assume the horizon will always be at the same point.)

        ys = []
        for i in lines:
            for ii in i:
                ys += [ii[1],ii[3]]
        min_y = min(ys)
        max_y = 600
        new_lines = []
        line_dict = {}

        for idx,i in enumerate(lines):
            for xyxy in i:
                # Used to calculate the definition of a line, given two sets of coords.
                x_coords = (xyxy[0],xyxy[2])
                y_coords = (xyxy[1],xyxy[3])
                A = vstack([x_coords,ones(len(x_coords))]).T
                m, b = lstsq(A, y_coords)[0]

                # Calculating our new, and improved, xs
                x1 = (min_y-b) / m
                x2 = (max_y-b) / m

                line_dict[idx] = [m,b,[int(x1), min_y, int(x2), max_y]]
                new_lines.append([int(x1), min_y, int(x2), max_y])

        final_lanes = {}

        for idx in line_dict:
            final_lanes_copy = final_lanes.copy()
            m = line_dict[idx][0]
            b = line_dict[idx][1]
            line = line_dict[idx][2]
            
            if len(final_lanes) == 0:
                final_lanes[m] = [ [m,b,line] ]
                
            else:
                found_copy = False

                for other_ms in final_lanes_copy:

                    if not found_copy:
                        if abs(other_ms*1.2) > abs(m) > abs(other_ms*0.8):
                            if abs(final_lanes_copy[other_ms][0][1]*1.2) > abs(b) > abs(final_lanes_copy[other_ms][0][1]*0.8):
                                final_lanes[other_ms].append([m,b,line])
                                found_copy = True
                                break
                        else:
                            final_lanes[m] = [ [m,b,line] ]

        line_counter = {}

        for lanes in final_lanes:
            line_counter[lanes] = len(final_lanes[lanes])

        top_lanes = sorted(line_counter.items(), key=lambda item: item[1])[::-1][:2]

        lane1_id = top_lanes[0][0]
        lane2_id = top_lanes[1][0]

        def average_lane(lane_data):
            x1s = []
            y1s = []
            x2s = []
            y2s = []
            for data in lane_data:
                x1s.append(data[2][0])
                y1s.append(data[2][1])
                x2s.append(data[2][2])
                y2s.append(data[2][3])
            return int(mean(x1s)), int(mean(y1s)), int(mean(x2s)), int(mean(y2s)) 

        l1_x1, l1_y1, l1_x2, l1_y2 = average_lane(final_lanes[lane1_id])
        l2_x1, l2_y1, l2_x2, l2_y2 = average_lane(final_lanes[lane2_id])

        return [l1_x1, l1_y1, l1_x2, l1_y2], [l2_x1, l2_y1, l2_x2, l2_y2], lane1_id, lane2_id
    except Exception as e:
        print(str(e))

# PROCESS THE IMAGE
# first convert the image to gray, then call edge detection on it, and finally pass it through a blur filter
def process_img(image):
    original_image = image
    # convert to gray
    processed_img = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    # edge detection
    processed_img =  cv2.Canny(processed_img, threshold1 = 200, threshold2=300)
    
    processed_img = cv2.GaussianBlur(processed_img,(5,5),0)
    
    vertices = np.array([[10,500],[10,300],[300,200],[500,200],[800,300],[800,500],
                         ], np.int32)

    processed_img = roi(processed_img, [vertices])

    #                                     rho   theta   thresh  min length, max gap:        
    lines = cv2.HoughLinesP(processed_img, 1, np.pi/180, 180,      20,       15)
    m1 = 0
    m2 = 0
    try:
        l1, l2, m1,m2 = draw_lanes(original_image,lines)
        cv2.line(original_image, (l1[0], l1[1]), (l1[2], l1[3]), [0,255,0], 30)
        cv2.line(original_image, (l2[0], l2[1]), (l2[2], l2[3]), [0,255,0], 30)
    except Exception as e:
        print(str(e))
        pass
    try:
        for coords in lines:
            coords = coords[0]
            try:
                cv2.line(processed_img, (coords[0], coords[1]), (coords[2], coords[3]), [255,0,0], 3)
                
                
            except Exception as e:
                print(str(e))
    except Exception as e:
        pass

    return processed_img,original_image, m1, m2


# DIRECTIONS

# move straight
def straight():
    PressKey(W)
    ReleaseKey(A)
    ReleaseKey(D)

# turn left
def left():
    PressKey(A)
    ReleaseKey(W)
    ReleaseKey(D)
    ReleaseKey(A)

# turn right
def right():
    PressKey(D)
    ReleaseKey(A)
    ReleaseKey(W)
    ReleaseKey(D)


#MAIN FUNCTION

def main():
    # COUNTDOWN
    for i in list(range(4)) [::-1]:
        print(i+1)
        time.sleep(1)
    last_time = time.time()

    # To be used by Random Player
    listOfMoves = [straight(), left(), right()]
    
    while True:
        #0,40,800,640
        screen =  np.array(ImageGrab.grab(bbox=(200, 300, 800, 600)))
        #screen =  grab_screen(region=(0, 40, 800, 640))
        screen = cv2.cvtColor(screen, cv2.COLOR_BGR2GRAY)
        # resize the screen to decrease computational time
        screen = cv2.resize(screen, (80, 60))
        keys = key_check()
        output = keys_to_output(keys)

        # Append data to file
        training_data.append([screen, output])
        
        #print('Frame took {} seconds'.format(time.time()-last_time))
        last_time = time.time()

        # indicate whenever 500 new data are added
        if len(training_data) % 500 == 0:
            print(len(training_data))
            np.save(file_name, training_data)
        
        #new_screen,original_image, m1, m2 = process_img(screen)
        #cv2.imshow('window', new_screen)
        #cv2.imshow('window2',cv2.cvtColor(original_image, cv2.COLOR_BGR2RGB))

# Random Player - randomly chooses a direction to move in

##        # Generate random integer every time
##        rand = random.randint(0, len(listOfMoves)-1)
##
##        # Execute the random move
##        listOfMoves[rand]


# Greedy Player - chooses the move guaranteed to bring the vehicle in between the driving lanes
#                 and thus this moves provides the best score solely on that move
##        # if lines appear to left of car
##        if m1 < 0 and m2 < 0:
##            right()
##        # if lines appear to right of car
##        elif m1 > 0  and m2 > 0:
##            left()
##        else:
##            straight()

        #cv2.imshow('window',cv2.cvtColor(screen, cv2.COLOR_BGR2RGB))
        if cv2.waitKey(25) & 0xFF == ord('q'):
            cv2.destroyAllWindows()
            break

# Run the main function
main()
