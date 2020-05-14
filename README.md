# Senti_AI
**About the app**

Senti AI is an android application that functions as your day to day personal diary. The android application is the client that sends the diary content posted by the user to the Amazon EC2 cloud which hosts flask file and machine learning models. The flask file takes the input request sends it to the machine learning models for prediction and it sends the response as happy or sad back to the client. The app was created to boost mental health of the users. It gives them a good perspective of their mental emotion everyday. This way users can understand them and be able to adjust their lifestyle. Example: Form good habits, Practice self-care, etc.

**Functionalities**
* Login and sign up.
* Room sqllite database to store diary content.
* Retrofit for GET and POST calls from android client to server.
* Amazon EC2 instance that hosts machine learning models and Flask server which provides RESTFUl API services.
* Machine learning model that analyzes sentiment of the request text.
* Proper usage of RecyclerView and android architecture components.
* Follows Google Material Design Guidelines.

**Flowchart**

![FlowChart](https://github.com/tamizh3110/Senti_AI/blob/master/flowchartaws.png)

<p><b>Walkthrough</b></p>
<br/>
<p align="center">
<img src="https://github.com/tamizh3110/Senti_AI/blob/master/Walkthrough.gif" width="400" height="800">
</p>

**Compatibility**

The android app currently supports Oreo (Android 8.0+) and Pie (Android 9.0+) versions.

**Updates**

* The Model needs to be fine-tuned for improved precision towards analyzing the sentiment of the text.
* Graph and Charts for visualizing sentiment trends have to be developed.
* Backward compatibility of the app can be improved.
* WorkManager class needs to be added to provide support for reminder.
* Settings preference needs to be provided to the user, so that the user can decide what features to turn on and off.


