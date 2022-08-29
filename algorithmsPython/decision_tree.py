#Indecisão = 0
#Camiseta = 1
#Calça = 2
#Short = 3
#Vestido = 4

import pandas as pd
import numpy as np
import os
import matplotlib.pyplot as plt 

from re import X
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
from sklearn.tree import export_text
from sklearn import tree

columns = [
    'Gênero',
    'Comprado',
    'Vendido',
    'Vendedor',
    'Estoque'
]

suggestion = ['', 'Camiseta', 'Calça', 'Short', 'Vestido']
target = "Sugestão"

def get_dataframe():
    if os.path.exists('dataframe.csv'):
        print("The dataframe.csv file was found locally")
        return pd.read_csv('dataframe.csv',
                   sep = ';',
                   engine = 'python')


df = get_dataframe()
X = df.drop(target, axis=1)
y = df[target]
features = list(df.columns[:5])

Xtrain, Xvalidation, ytrain, yvalidation = train_test_split(X, y, test_size=0.5, random_state=0)

decision_tree = DecisionTreeClassifier()
decision_tree.fit(Xtrain, ytrain)
result_predict = decision_tree.predict(Xvalidation)
y_train_pred = decision_tree.predict(Xtrain)
y_test_pred = decision_tree.predict(Xvalidation)

print("\nMean squared error: ", np.sqrt(mean_squared_error(yvalidation, result_predict)))
print(f"Training Accuracy: {round(accuracy_score(ytrain, y_train_pred), 2)}")
print(f"Test Accuracy: {round(accuracy_score(yvalidation, y_test_pred), 2)}")
print("Total accuracy: %f" % accuracy_score(yvalidation, result_predict))
print('\n ------------ Confusion matrix ---------------\n')
print(pd.crosstab(yvalidation, result_predict, rownames=['Actual'], colnames=['Predicted'], margins=True))
print('\n')

def convert_listStr_to_listInt(list):
    int_list = []
    int_list.append(list.split(','))
    return int_list

def get_suggestion_purchase(data):
    df = pd.DataFrame(convert_listStr_to_listInt(data), columns=columns)
    result = decision_tree.predict(df)
    result_int = int(np.array_str(result).replace('[','').replace(']','')) 
    return suggestion[result_int]

itens = '1,2,0,0,2'
print('Sugestion: ', get_suggestion_purchase(itens))
print('\n')

#text_representation = export_text(decision_tree)
#with open("decistion_tree.log", "w") as fout:
#    fout.write(text_representation)
#fig = plt.figure(figsize=(25,20))
#_ = tree.plot_tree(decision_tree, 
#                   feature_names=features,  
#                   class_names=target,
#                   filled=True)
#fig.savefig("decistion_tree.png")


# Print the Dataframe
#print(df)
#print("* df.head()", df.head(), sep="\n", end="\n\n")
#print("* df.tail()", df.tail(), sep="\n", end="\n\n")
#print("Sugestão: ", df["Sugestão"].unique())
#print("Features: ", features)
