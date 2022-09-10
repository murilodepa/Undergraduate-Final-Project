"""
TCC
"""
import re
import pandas as pd
from flask import Flask, request, jsonify
from decision_tree import decision_tree

from decision_tree import get_suggestion_purchase
from match_making import get_seller_to_attendance

app = Flask(__name__)

# Communication endpoint to receive client purchase seggestion
@app.route("/postPurchaseSuggestion", methods=["POST"])
def post_decision_tree():
    try:
        request_decision_tree = request.json
        # Return purchase suggestion
        print("Params: ", request_decision_tree["content"])
        return get_suggestion_purchase(request_decision_tree["content"])
    except:
        print("Error in request json!")
        return None

columns = [
    'id',
    'gender',
    'sector',
    'age'
]

# Communication endpoint to receive a json file with sellers and client informations
@app.route("/postBestSeller", methods=["POST"])
def post_matchmaking():
    try:
        request_data = request.json["content"]
        seller_data = []
        client_data = []
        print(request_data)

        if request_data != None and len(request_data) > 1: 
            request_data = request_data.split("/")
            print("Request_data", request_data)
            last_position = (len(request_data)-1)
            client_data.append(request_data[last_position].split(','))
            print("client_data", client_data)
            for i in range(last_position):
                seller_data.append(request_data[i].split(','))
            
        seller_df = pd.DataFrame(seller_data, columns=columns)
        client_df = pd.DataFrame(client_data, columns=columns)
        return get_seller_to_attendance(seller_df, client_df)
        # Return id of the seller to attendance the client
    except:
        print("Error in request json!")
        return None

if __name__ == '__main__':
    app.run(port=9090)