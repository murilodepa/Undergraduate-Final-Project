#Empate = 0
#Camiseta = 1
#Calça = 2
#Calça = 3
#Vestido = 4

from dataclasses import replace
from distutils.cmd import Command
from io import StringIO 
import os
from re import X
import subprocess
import graphviz
import pydotplus

# Importing pandas library
import pandas as pd
import numpy as np
from sklearn.tree import DecisionTreeClassifier, export_graphviz, plot_tree, export_text
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error
from pydot import graph_from_dot_data
from IPython.display import Image
from matplotlib import pyplot as plt
from sklearn import datasets
from sklearn.tree import DecisionTreeClassifier 
from sklearn import tree
from dtreeviz.trees import dtreeviz

# Load the data of example.csv
# with '_' as custom delimiter
# into a Dataframe df

suggestion = ['', 'Camiseta', 'Calça', 'Calça', 'Vestido']
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
features = list(df.columns[:4])

Xtrain, Xvalidation, ytrain, yvalidation = train_test_split(X, y, test_size=0.5, random_state=0)
decision_tree = DecisionTreeClassifier()
decision_tree.fit(Xtrain, ytrain)

predict = decision_tree.predict(Xvalidation)

print(np.sqrt(mean_squared_error(yvalidation, predict)))


itens = [[0, 1, 0, 3]]

columns = [
    'Comprado',
    'Vendido',
    'Vendedor',
    'Estoque'
]

test = pd.DataFrame(itens, columns=columns)


print(decision_tree.predict(test))

text_representation = export_text(decision_tree)
with open("decistion_tree.log", "w") as fout:
    fout.write(text_representation)
fig = plt.figure(figsize=(25,20))
_ = tree.plot_tree(decision_tree, 
                   feature_names=features,  
                   class_names=target,
                   filled=True)
fig.savefig("decistion_tree.png")




# Print the Dataframe
#print(df)
#print("* df.head()", df.head(), sep="\n", end="\n\n")
#print("* df.tail()", df.tail(), sep="\n", end="\n\n")
#print("Sugestão: ", df["Sugestão"].unique())
#print("Features: ", features)
