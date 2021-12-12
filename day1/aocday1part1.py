# System libraries


# Local libraries
from libraries import common
from libraries import day1


if __name__ == '__main__':
    input_file = 'day1/input'
    file_handler = common.FileHandler(input_file)
    input_data = file_handler.int_data_from_text()
    
    data_handler = day1.DataHandler(input_data)
    print(data_handler.get_larger_count(input_data))
