import numpy as np

# Sigmoid and its derivative
def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def sigmoid_derivative(x):
    return x * (1 - x)

# XOR dataset
inputs = np.array([[0, 0],
                   [0, 1],
                   [1, 0],
                   [1, 1]])

targets = np.array([[0], [1], [1], [0]])

# Initialize weights and biases
np.random.seed(42)
a = 2
b = 2
c = 1

# Weights
d = np.random.uniform(size=(a, b))
e = np.random.uniform(size=(b,c))

# Biases
f = np.random.uniform(size=(1, b))
g = np.random.uniform(size=(1, c))

# Training
epochs = 10000
learning_rate = 0.1

for epoch in range(epochs):
    # Forward pass
    h = np.dot(inputs, d) + f
    i = sigmoid(h)

    j = np.dot(i,e) + g
    k = sigmoid(j)

    # Backpropagation
    error = targets - k
    d_output = error * sigmoid_derivative(k)

    error_hidden = d_output.dot(e.T)
    d_hidden = error_hidden * sigmoid_derivative(i)

    # Update weights and biases
    e += i.T.dot(d_output) * learning_rate
    d += inputs.T.dot(d_hidden) * learning_rate
    g += np.sum(d_output, axis=0, keepdims=True) * learning_rate
    f += np.sum(d_hidden, axis=0, keepdims=True) * learning_rate

# Final predictions
print("Final output after training:")
print(np.round(k, 3))
