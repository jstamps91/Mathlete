# Mathlete
## Introduction
The system is a math fluency program for children, which allows children to practice math problems to increase fluency using either a mobile device or a web browser. The system will track a child’s progress over time and automatically adapt the problems presented to the child to increase mastery. In addition, the system will provide reports to teachers and parents. The system will allow teachers to organize groups to facilitate monitoring. The system also permits parents to register children for independent study. The system performs an initial evaluation session with each child to determine the appropriate beginning fluency level for the child.

## User Requirements Definition
The three principal actors are the teacher, parent, and a child. A child can log in or start a math session. A teacher can create a group, print registration fliers with a registration code for parents to register their children, email registration fliers to parents, or review a child’s progress. Parents can register children with a registration code from a teacher or register a child independently, as well as review a child’s progress.

## System Requirements Specification
The system requirements include functional requirements which describe what the software system should do and non-functional requirements which place constraints on how the system meets the functional requirements.

### Functional Requirements
* The system must allow teachers to create “class” groups and print or email registration fliers to invite users to these groups.
* The system must allow parents to register children using a supplied registration flier or as an individual user not connected to a group.
* For group registration the system must allow the teacher to select a grade level which corresponds to standards that determine which problems are presented to the child user. For individual registration by a parent, the parent can select the grade level or allow the system to test the child user and automatically select a level to match the user’s proficiency.
* The system should track the child’s progress and automatically adjust the difficulty of the problems presented.
* The system should email a status report to the child’s parent each week.
* The system should email a group status report to the teacher each week.
 
### Non-Functional Requirements
* The system should provide a user interface via a browser or Android app.

## System Evolution
This section details assumption on the evolution of the system.

1. The initial implementation will be very basic and just present basic math problems.
2. A subsequent implementation may include different types of problems related to the corresponding grade level standards as well as a more attractive interface.
3. A subsequent implementation may include the ability to base the selected standards off of a state selection due to differences in standards by state.
4. A subsequent implementation may include the addition of gamification techniques to alter the flow of the user experience. 
5. The ultimate goal is an application that looks like Duolingo for math.
