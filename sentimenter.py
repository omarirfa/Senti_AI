from flask import Flask,request,jsonify
import re
import tensorflow
import keras
import numpy as np
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
from tensorflow.keras.models import model_from_json


app=Flask(__name__)

@app.route("/sentimenter",methods=['POST'])
def predict():
    if request.method=='POST':
        request_string=request.get_data().decode("utf-8")
        sentence=[request_string.replace('"','')]
        print(sentence)
        emptyli = []
        for sen in sentence:
            emptyli.append(preprocess_text(sen))
        
        tokenizer = Tokenizer(num_words=None)
        input_instance = tokenizer.fit_on_texts(emptyli)    
        input_instance = tokenizer.texts_to_sequences(emptyli)
        flat_list = []
        for sublist in input_instance:
            for item in sublist:
                flat_list.append(item)
        maxlen=100
        flat_list = [flat_list]
        #pad instance based on maxlen
        input_instance = pad_sequences(flat_list, padding='post', maxlen=maxlen)
        loaded_model=model_load()
        loaded_model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
        score = loaded_model.predict(input_instance, verbose=1)
        lstm_ropre = np.round(score,2)
        lstm_ropre = np.squeeze(lstm_ropre)
        print(lstm_ropre)
        if(lstm_ropre>=0.4):
            return jsonify("Happy")        
        else:
            return jsonify("Sad")
        
def model_load():
    json_file = open("/home/tamizh3110/vs_code_projects/flasky/model.json", 'r')
    loaded_model_json = json_file.read()
    json_file.close()
    loaded_model = model_from_json(loaded_model_json)
    # load weights into new model
    loaded_model.load_weights("/home/tamizh3110/vs_code_projects/flasky/model.h5")
    print("Loaded model from disk")
    return loaded_model
        
def preprocess_text(sen):
    
    sentence = remove_tags(sen)
    sentence = re.sub('[^a-zA-Z]', ' ', sentence)
    sentence = re.sub(r"\s+[a-zA-Z]\s+", ' ', sentence)
    sentence = re.sub(r'\s+', ' ', sentence)
    return sentence

def remove_tags(text):

    TAG_RE = re.compile(r'<[^>]+>')
    return TAG_RE.sub('', text)







if __name__=="__main__":
    app.run(debug=True)
