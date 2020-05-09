# Senti_AI
Senti AI is an android application that functions as your day to day personal diary. The android application is the client that sends the diary content posted by the user to the Amazon EC2 cloud which hosts flask file and machine learning models.The flask file takes the input request sends it to the machine learning models for prediction and it sends the response as happy or sad back to the client. The app was created to boost mental health of the users. It gives them a good perspective of their mental emotion everyday. This way users can understand them and be able to adjust their lifestyle. Example: Form good habits, Practice self-care, etc.

Functionalities
1. Login and sign up.
2. Room sqllite database to store diary content.
3. Retrofit for GET and POST calls from android client to server.
4. Amazon EC2 instance that hosts machine learning models and Flask server which provides RESTFUl API services.
5. Machine learning model that analyzes sentiment of the request text.
6. Proper usage of RecyclerView and android architecture components.
7. Follows Google Material Design Guidelines.

FlowChart

![FlowChart](https://github.com/tamizh3110/Senti_AI/blob/master/flowchartaws.png)
