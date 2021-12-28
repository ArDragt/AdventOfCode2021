# System libraries

# PyPi libraries
import numpy as np

# Local libraries
from libraries import common
from libraries import day6


if __name__ == '__main__':
    input_fn = 'day6/input'
    file_handler = common.FileHandler(input_fn)
    raw_data = file_handler.read_text_data()
    lantern_data = raw_data[0].split(',')

    school = day6.School()
    for entry in lantern_data:
        school.increment(int(entry), 1)

    for day in range(1,257):
        school.cycle()
        school.merge_changes()

    print(school.get_total_count())
