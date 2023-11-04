# Project 3 Prep

**What distinguishes a hallway from a room? How are they similar?**

Answer:
The width and height of rooms are random but hallway have a width of 1 tile and a random length.
They both have walls that are visually distinct from floors.

**Can you think of an analogy between the process of 
drawing a knight world and randomly generating a world 
using rooms and hallways?**

Answer:
Creating a knight world is like developing a planned neighborhood with a very strict pattern.
Randomly generating a world using rooms and hallways are a mix of RandomWorldDemo and KnightWorld, which the place
are of room and hallways are random but they have strict rules like they need to be connected.

**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually 
get to the full knight world.**

Answer:
Constructor of the grid and generator of the room class and hallway class which are restricted by rules.

**This question is open-ended. Write some classes 
and methods that might be useful for Project 3. Think 
about what helper methods and classes you used for the lab!**

Answer:
class rooms(){
    generateRoom(){
};
    randomGenerateAllRooms() {
};
    isConnectedToHallways() {
};
}

class hallways(){
    generateHallWays() {
};
    randomGenerateAllHallWays() {
};
    isConnectedToRoom() {
};
}

