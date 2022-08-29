"""
TCC
"""
import re
from flask import Flask, request, jsonify
from decision_tree import decision_tree

from decision_tree import get_suggestion_purchase

app = Flask(__name__)

# Communication endpoint to receive client purchase seggestion
@app.route("/postPurchaseSuggestion", methods=["POST"])
def post_decision_tree():
    try:
        request_decision_tree = request.json
        # Return purchase suggestion
        return get_suggestion_purchase(request_decision_tree["content"])
    except:
        print("Error in request json!")
        return None

if __name__ == '__main__':
    app.run(port=9090)