# System libraries
from pathlib import Path
import csv


class FileHandler:
    def __init__(self, input_filename) -> None:
        self.base_path = Path(__file__).parent
        self.root_path = self.base_path.parent
        self.input_path = self.root_path.joinpath(input_filename)

    def read_int_data(self):
        with open(self.input_path, 'r') as _input_file:
            return [int(_row.strip()) for _row in _input_file]

    def read_text_data(self):
        with open(self.input_path, 'r') as _input_file:
            return [_row.strip() for _row in _input_file]

    def read_csv(self, delimiter=','):
        with open(self.input_path, 'r') as _input_file:
            csv_reader = csv.reader(_input_file, delimiter=delimiter)
            return [_row for _row in csv_reader]
