# System libraries

# PyPi libraries
import numpy as np

# Local libraries
from libraries import common
from libraries import day5


if __name__ == '__main__':
    input_fn = 'day5/input'
    file_handler = common.FileHandler(input_fn)
    lines_data = file_handler.read_text_data()

    vents = day5.Vents()

    for entry in lines_data:
        line = day5.Line(entry)
        if line.check_xy_match() or line.check_45_degrees():
            line_points = line.traverse_line()
            for point in line_points:
                vents.increment_point_counter(point)

    print(vents.count_overlaps())
    