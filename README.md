# JavaFX_Calculator

A standared calculator I created from scratch (GUI was created solely with JavaFX).  All the basic functionalities and operations are working properly.

<b>Work in progress.</b>

<h1>Images</h1>

![alt tag](http://i.imgur.com/BEs0ZLb.png)

<h1>Remarks</h1>

This is a personal project that was intended for familiarizing myself with JavaFX and event handling. Through this project, I gained insight into how complicated the logic behind a simple calculator application can be. One interesting case was my implementation for the backspace button. Originally, pressing the backspace button just deleted the last character of the output text box. But this clearly did not work for negative integers since pressing backspace for "-5" led to "-" ... so I added a check for negative integers. I also spent quite a bit of time thinking of an implementation for storing the inputs. Currently, the inputs are stored in a list but I believe this is not the most optimal way. Another case to consider was for displaying integers without trailing zeros (e.g. "12" instead of "12.0) - but that was simple to fix with a simple check.

<h1>Planned Updates</h1>
<ul>
<li>better error handling for cases such as division by zero or large numbers</li>
<li>update GUI</li>
<li>refactoring redundant code by using interfaces/abstract classes</li>
</ul>


