# 🐍 Snake Game - Java
A classic Snake game implementation in Java using Swing for GUI. Features player system, score tracking, and customizable snake colors.
## ✨ Features

- **Player registration** with name input
- **Snake customization** - 6 color options
- **Score system** with persistent tracking
- **Food mechanics** - snake grows when eating
- **Collision detection** with walls and self
- **Responsive controls** with keyboard input
- **Visual grid** with enhanced gameplay area

## 🚀 Getting Started

### Prerequisites
- Java JDK 11+
- Maven (for building)

### Installation
1. Clone the repository:

bash
git clone https://github.com/alexbzz/snake_java.git
cd snake-game-java
mvn compile exec:java -Dexec.mainClass="Main"
java -jar target/snake-game.jar


# 🎮 How to Play Snake Game

## 🕹️ Basic Controls
Use your **keyboard arrow keys** to control the snake:
- **↑** (Up Arrow): Move upward
- **↓** (Down Arrow): Move downward
- **←** (Left Arrow): Move left
- **→** (Right Arrow): Move right

## 🍎 Game Objective
- Eat the **red food** to make your snake grow
- Each food eaten:
    - Adds +1 to your score
    - Increases your snake's length by one segment
- Avoid hitting walls or biting your own tail

## 🏁 Getting Started
1. **Launch the game**: Run `Main.java`
2. **Register**:
    - Enter your name
    - Choose a color for your snake
    - Click "Start Game"

## 🚦 Important Rules
- Game speed increases slightly as you progress
- You cannot make 180° turns (e.g., can't go directly from right to left)
- Game ends when:
    - You hit a wall
    - You collide with your own tail

## 💡 Pro Tips
- Try **making wide turns** to avoid collisions
- **Plan ahead** by thinking several moves in advance
- Corners are dangerous zones - be careful!

## 🖥️ Game Display
- Your **current score** appears at the top
- **Snake length** is visible by segment count
- Visual effect appears when eating food

## 🔄 Restarting
After game over:
1. Game shows your final score
2. Close the window
3. Relaunch to play again

# 🐍 Snake Game - Java

## 🧠 How It Works

### 🏗️ Core Architecture
The game follows a **Model-View-Controller (MVC)** pattern:
- **Model**: `Snake`, `Food`, and `Player` classes handle game logic
- **View**: `GameWindow`, `GameLayer` render the GUI
- **Controller**: `Keyboard` handles user input

### 🔄 Game Loop
The main game flow is driven by a `javax.swing.Timer` that triggers updates at fixed intervals (200ms by default):
java
timer = new Timer(200, e -> {
snake.move();
checkCollisions();
updateScoreDisplay();
gamePanel.repaint();
});