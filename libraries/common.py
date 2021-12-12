# System libraries
from pathlib import Path


class FileHandler:
    def __init__(self, input_filename) -> None:
        self.base_path = Path(__file__).parent
        self.root_path = self.base_path.parent
        self.input_path = self.root_path.joinpath(input_filename)

    def int_data_from_text(self):
        with open(self.input_path, 'r') as _input_file:
            return [int(_row.strip()) for _row in _input_file]

