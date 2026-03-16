# Creating Lists in python

# 1. Using Square Brackets []: This is the most common method. You can include items separated by commas inside the brackets, or leave them empty to create an empty list. Lists can hold items of different data types within the same list.
# An empty list
empty_list = []

# A list of integers
numbers = [1, 2, 3, 4, 5]

# A list of strings
fruits = ["apple", "banana", "cherry"]

# A list with mixed data types (integer, string, float, boolean)
mixed_list = [1, "hello", 3.14, True]

# A list of lists (nested list)
matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]


# 2. Using the list() Constructor : The built-in list() function can convert other iterable data types (like tuples, strings, or ranges) into a list.
# From a tuple
my_tuple = (1, 2, 3, 4, 5)
list_from_tuple = list(my_tuple)
# Output: [1, 2, 3, 4, 5]

# From a string (each character becomes an item)
list_from_string = list("Python")
# Output: ['P', 'y', 't', 'h', 'o', 'n']

# From a range of numbers
list_from_range = list(range(1, 6)) # Numbers from 1 up to (but not including) 6
# Output: [1, 2, 3, 4, 5]

# 3. Using List Comprehension : List comprehension provides a concise way to create lists based on an existing iterable, often involving a loop and an optional condition in a single line of code. 

