class DataHandler:
    def __init__(self) -> None:
        self.position_tracker = {
            0: {'ones': 0, 'zeros': 0},
            1: {'ones': 0, 'zeros': 0},
            2: {'ones': 0, 'zeros': 0},
            3: {'ones': 0, 'zeros': 0},
            4: {'ones': 0, 'zeros': 0},
            5: {'ones': 0, 'zeros': 0},
            6: {'ones': 0, 'zeros': 0},
            7: {'ones': 0, 'zeros': 0},
            8: {'ones': 0, 'zeros': 0},
            9: {'ones': 0, 'zeros': 0},
            10: {'ones': 0, 'zeros': 0},
            11: {'ones': 0, 'zeros': 0}
        }
    
    def increment_tracker(self, report_row):
        for count, bit in enumerate(report_row):
            if bit == '1':
                self.position_tracker[count]['ones'] += 1
            elif bit == '0':
                self.position_tracker[count]['zeros'] += 1

    def increment_tracker_bit(self, report_row, position):
        if report_row[position] == '1':
            self.position_tracker[position]['ones'] += 1
        elif report_row[position] == '0':
            self.position_tracker[position]['zeros'] += 1

    def get_most_common(self, position: int) -> str:
        if self.position_tracker[position]['ones'] > self.position_tracker[position]['zeros']:
            return '1'
        else:
            return '0'

    def get_least_common(self, position: int) -> str:
        if self.position_tracker[position]['ones'] < self.position_tracker[position]['zeros']:
            return '1'
        else:
            return '0'

    def get_bit_count(self, position, key):
        return self.position_tracker[position][key]

    @staticmethod
    def filter_rows(input_data, position, bit_value):
        return [row for row in input_data if row[position] == bit_value]

    @staticmethod
    def get_decimal(bin_string) -> int:
        return int(bin_string, 2)
        