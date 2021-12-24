# System libraries
import copy

# Local libraries
from libraries import common
from libraries import day3


if __name__ == '__main__':
    input_file = 'day3/input'
    file_handler = common.FileHandler(input_file)
    data = file_handler.read_text_data()

    oxygen_data_handler = day3.DataHandler()
    oxygen_data = copy.deepcopy(data)

    co2_data_handler = day3.DataHandler()
    co2_data = copy.deepcopy(data)
    
    for position in range(0,12):
        if len(oxygen_data) > 1:
            for measurement in oxygen_data:
                oxygen_data_handler.increment_tracker_bit(measurement, position)

            oxygen_ones_count = oxygen_data_handler.get_bit_count(position, 'ones')
            oxygen_zeros_count = oxygen_data_handler.get_bit_count(position, 'zeros')
            if oxygen_ones_count >= oxygen_zeros_count:
                oxygen_data = oxygen_data_handler.filter_rows(oxygen_data, position, '1')
            else:
                oxygen_data = oxygen_data_handler.filter_rows(oxygen_data, position, '0')

        if len(co2_data) > 1:
            for measurement in co2_data:
                co2_data_handler.increment_tracker_bit(measurement, position)

            co2_ones_count = co2_data_handler.get_bit_count(position, 'ones')
            co2_zeros_count = co2_data_handler.get_bit_count(position, 'zeros')
            if co2_zeros_count <= co2_ones_count:
                co2_data = co2_data_handler.filter_rows(co2_data, position, '0')
            else:
                co2_data = co2_data_handler.filter_rows(co2_data, position, '1')

    print(co2_data_handler.get_decimal(oxygen_data[0]) * co2_data_handler.get_decimal(co2_data[0]))
