# System libraries


# Local libraries
from libraries import common
from libraries import day2

if __name__ == '__main__':
    input_file = 'day2/input'
    file_handler = common.FileHandler(input_file)
    data = file_handler.read_text_data()
    location_calculator = day2.Locator()

    for direction in data:
        direction_parts = location_calculator.parse_move(direction)
        location_calculator.move(direction_parts[0], int(direction_parts[1]))
    
    distance = location_calculator.get_distance()
    depth = location_calculator.get_depth()
    print(distance*depth)
