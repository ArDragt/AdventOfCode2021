class DataHandler:
    def __init__(self, data) -> None:
        self.data = data
        self.grouped_data = []

    def get_group_sums(self, group_size: int) -> list:
        for _count, _measurement in enumerate(self.data):
            _values_group = self.data[_count:_count + group_size]
            if len(_values_group) == group_size:
                self.grouped_data.append(sum(_values_group))
        return self.grouped_data

    @staticmethod
    def get_larger_count(data):
        _larger = []

        for _count, _measurement in enumerate(data):
            if _count == 0:
                continue
            else:
                _previous = int(data[_count - 1])
                if int(_measurement) > _previous:
                    _larger.append(_measurement)
    
        return len(_larger)
