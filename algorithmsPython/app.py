"""
TCC
"""

import re
from flask import Flask, request, jsonify
from decision_tree import decision_tree

app = Flask(__name__)

# Communication endpoint to send a json file and to receive a json file with foods informations of input file
@app.route("/postPurchaseSuggestion", methods=["POST"])
def post_decision_tree():
    try:
        request_decision_tree = request.json
        verify_suggestion = request_decision_tree["content"]
        print("CHEGOUU: ", verify_suggestion)
        
        # Return json file with purchase suggestion
        return "result"
    except:
        print("Error in request json!")
        return None

if __name__ == '__main__':
    app.run(port=9090)