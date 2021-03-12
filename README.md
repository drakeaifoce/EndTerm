# Quiz Management System 
> Intellectual quiz game for teams and for solo play.



Our program is designed for intellectual games. You can take the test either alone or in team mode for 2 people. You can create a minimum of 4 people. There is a main administrator who can add questions, view questions separately with answers, or can display the entire list of questions from the database. Participants can view their results after each game.

![](header.jpg)


## Classes description
   Database folder: PostgreDB class, implementation of IDB interface, which connects application to database.   
   
   Enteties folder: Player entity for single quiz,  
                    Team entity for team quiz,  
                    Question entity for questios.  
                    
   Repositories folder: Speccific appeal to the datadase.
   
   Controllers folder: Controllers is used to manage system or manage quiz during the session.
   
   Admin class: admin tool, which allows to manage the questions as admin
   
   Quiz class: quiz by itself for team and single session.
   
## Release History

* 0.2.1
    * CHANGE: Update docs (module code remains unchanged)
* 0.2.0
    * CHANGE: `Write readme`
    * ADD: Add `comments`
* 0.1.1
    * FIX: Crash when calling `Score` 
* 0.1.0
    * The first proper release
   * 0.0.1
    * Work in progress


## Meta

Kuanyshbek Mustafin – mr.kuanysh2003@gmail.com
Darkhan Iliyas – drakeaifoce@gmail.com

Distributed under the XYZ license. See ``LICENSE`` for more information.

[https://github.com/drakeaifoce/EndTerm]

