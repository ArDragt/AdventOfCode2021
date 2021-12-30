# System libraries

# PyPi libraries
import numpy as np

# Local libraries
from libraries import common
from libraries import day7


if __name__ == '__main__':
    input_fn = 'day7/input'
    file_handler = common.FileHandler(input_fn)
    raw_data = file_handler.read_text_data()
    raw_list = raw_data[0].split(',')
    crab_data = [int(row) for row in raw_list]

    data_handler = day7.DataHandler()

    for crab in crab_data:
        data_handler.add_crab(crab)

    for common_location in range(data_handler.get_min_location(), data_handler.get_max_location() + 1):
        for crab_location in data_handler.locations.keys():
            if crab_location == common_location:
                continue
            data_handler.move_increasing_cost(crab_location, common_location)

    lowest_cost = data_handler.get_lowest_cost()
    print(lowest_cost)
