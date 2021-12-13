# System libraries


# Local libraries
from libraries import common
from libraries import day3

if __name__ == '__main__':
    input_file = 'day3/input'
    file_handler = common.FileHandler(input_file)
    data = file_handler.read_text_data()
    data_handler = day3.DataHandler()
    for measurement in data:
        data_handler.increment_tracker(measurement)

    gamma_bits = []
    epsilon_bits = []
    
    # Get gamma reading
    for bit in data_handler.position_tracker.keys():
        gamma_bits.append(data_handler.get_most_common(bit))
    gamma_str = "".join(gamma_bits)
    gamma = int(gamma_str, 2)

    # Get epsilon reading
    for bit in data_handler.position_tracker.keys():
        epsilon_bits.append(data_handler.get_least_common(bit))
    epsilon_str = "".join(epsilon_bits)
    epsilon = int(epsilon_str, 2)

    print(gamma*epsilon)
