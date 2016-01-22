Title:          Week 1 - Singleton Pattern Program (Track Lane Manager)
Author:         Elijah Cornell
Creation Date:  2016-01-19
Class:          PRG/421 - Roland Morales

Program Requirements:

 Key parts:
   - A private static variable to store the single instance called the singleton
   - A public static method for callers to get a reference to the instance
   - A private constructor so no callers can instantiate the object directly

 Allow a user of the program to assign only one runner to each of the 8 lanes
 of running track in a field.

 Both the TrackManager and UI classes utilize the singleton pattern

 Program Flow:
    Display a main menu
         -> Assign runner to lane
         -> List current lane assignments
         -> Reset lane assignments
         -> Exit

Input: Console
Output: Console


To Run: java -cp .\out\production\Week1 ecornell.wk1.singleton.Main