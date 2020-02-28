# Distributed Shared Whiteboard

## Introduction

* Project 2 of [COMP90015](https://handbook.unimelb.edu.au/2019/subjects/comp90015) (Distributed Systems) at the University of Melbourne, 2019 Sem2.

A distributed shared whiteboard application that allows multiple users to draw shapes and chat on the same whiteboard simultaneously, user will have different degree of control over the whiteboard based on their role below:

1. Mananger: whoever creates the whiteboard, could chat and draw on the whiteboard, save or open pre-saved whiteboards, manager whiteboard members (allow join, kick out ...).

2. Normal user: whoever join one of the existing whiteboard with the permission of manager, could only chat and draw with others.

Multiple whiteboards could be created and users could choose whatever one they want to join.

User need to log in / sign up to user the application, all passwords are encrypted by PBKDF2WithHmacSHA512 algorithms.

## System Design and Choice

**System architecture**: 

* 3-tier Client-server architecture. Client <-> Whiteboard Server <-> Data Server

* Within each side, we have: presentation layer <-> application layer <-> data layer

**Communication protocol**: 

* Java RMI: all communication between whiteboard server and data server, clients send request to whiteboard server.

* MQTT: whiteboard server publish updates to all subscribed clients.

**GUI**: JavaFX

## Contribution

- Zihan Zhang: zhazz@student.unimelb.edu.au

- Guang Yang: gyya@student.unimelb.edu.au

- Chengeng Liu: chengengl@student.unimelb.edu.au

- Xiuge Chen: xiugec@student.unimelb.edu.au

  
