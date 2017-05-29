JARVIS
 
GitHub: https://github.com/The-Browning-Knight/JARVIS
 
Overview:
In this design project, we plan to use neural networks to design a self-driving car in GTA V.  To be able to maneuver GTA’s virtual reality, the car detects driving lanes.  There are three different agents: Random Player, Greedy Player, and Neural Network Player.  The random player simply takes in its environment, and outputs a random direction to move in (up, right, or left).  On the other hand, the greedy player makes a decision on its current state. It looks at its location relative to the driving lanes and chooses a move bringing it in between the lanes.   This is done using OpenCV, a program that identifies lanes via edge detection and plots lines onto them.   The Neural Network Player takes test data in from of keys pressed and photos, and then learns to drive around by itself.  As the user drives around in GTA, the program records the orientation of driving lanes along with any keys the user pressed at that moment. However, due to NEAT’s very long processing time on our computers, we could only get 2 very simple data samples onto it, so we abandoned the idea of inputting into NEAT. All of the agents will be from a first-person point of view.
 
To Run the Neural Net (Not with the Game)
Open run.java and run
 
Special Instructions (With the Game):
	Download and Install:
		Grand Theft Auto V
		OpenCV
		Python 2.7
		Pywin32
	Install the following Python libraries (using “pip install …”):
		numpy 
		cv2
		statistics
		image
		win32gui, win32api, win32ui  (need Pywin32 for this)

	Download all files located in the JARVIS folder
	Extract all the files

	For Random Player: Open pygta5_RandomPlayer and run the program**
	For Greedy Player: Open pygta5_GreedyPlayer and run the program**
	For NeuralNet Player: Open pygta5_NeuralNetPlayer and run the program**

**Make sure GTA is open and running in the corner.  Move the windowed game screen so that it aligns with the Python window.
