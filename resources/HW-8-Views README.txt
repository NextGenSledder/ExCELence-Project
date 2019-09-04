Changes we made to our code for our consumers:
- We made our Keyframe and Property classes interfaces and implemented them into classes.
	--> We did this so we could give our consumers our versions of keyframes and they were supposed to have interfaves in the first place.

- We referenced a utility class in our EditView that we couldn't give our consumers. 
	--> We changed this so that the utility class was used in the controller implementation class.