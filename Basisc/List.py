### <--------------CREATING LISTS----------------->

#<-------   1. Using Square Brackets []: --------->
# This is the most common method. You can include items separated by commas inside the brackets, or leave them empty to create an empty list. Lists can hold items of different data types within the same list.

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


# <------------2. Using the list() Constructor : ---------->
# The built-in list() function can convert other iterable data types (like tuples, strings, or ranges) into a list.
# From a tuple
my_tuple = (1, 2, 3, 4, 5)
list_from_tuple = list(my_tuple)  # Output: [1, 2, 3, 4, 5]
# From a string (each character becomes an item)
list_from_string = list("Python")  # Output: ['P', 'y', 't', 'h', 'o', 'n']

# From a range of numbers
list_from_range = list(range(1, 6)) # Numbers from 1 up to (but not including) 6
# Output: [1, 2, 3, 4, 5]

#<--------3. Using List Comprehension :---------->
# List comprehension provides a concise way to create lists based on an existing iterable, often involving a loop and an optional condition in a single line of code. 
# Create a list of squares for numbers from 0 to 9
squares = [x**2 for x in range(10)]  # Output: [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
# Create a list of even numbers from 0 to 10
even_numbers = [x for x in range(11) if x % 2 == 0] # Output: [0, 2, 4, 6, 8, 10]

#4. <---------Creating Lists with Repeated Elements----------->
# You can create a list with repeated elements by multiplying a list by an integer. This will repeat the elements of the list a specified number of times.
# Create a list with 5 zeroes
zero_list = [0] * 5 # Output: [0, 0, 0, 0, 0]
# Create a list with 3 "hello" strings
hello_list = ["hello"] * 3  # Output: ['hello', 'hello', 'hello']

#5. Using the copy() Method : You can create a new list by copying an existing list using the copy() method, which creates a shallow copy of the list.
original_list = [1, 2, 3, 4, 5]
copied_list = original_list.copy() # Output: [1, 2, 3, 4, 5]




#-------------------APPENDING TO A LIST----------------->
# You can add elements to the end of a list using the append() method, which is an O(1) operation.
my_list = [1, 2, 3]
my_list.append(4)  # Output: [1, 2, 3, 4]

#-------------------INSERTING INTO A LIST----------------->
# To insert an element at a specific position in a list, you can use the insert() method. This method takes two arguments: the index where you want to insert the element and the element itself. Note that inserting at the beginning of a list (index 0) is an O(n) operation because it requires shifting all existing elements to the right.
my_list = [1, 2, 3]
my_list.insert(1, 4)  # Output: [1, 4, 2, 3]




