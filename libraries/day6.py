class School:
    def __init__(self) -> None:
        self.tracker = {0: 0, 1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6:0, 7:0, 8: 0}
        self.changes = {6:0, 8: 0}

    def increment(self, key, by):
        self.tracker[key] += by

    def cycle(self):
        for k, v in self.tracker.items():
            if v == 0:
                continue
            elif k == 0:
                self.changes[6] += v
                self.changes[8] += v
                self.tracker[k] -= v
            else:
                self.tracker[k] -= v
                self.tracker[k-1] += v

    def merge_changes(self):
        for k, v in self.changes.items():
            self.tracker[k] += v
            self.changes[k] = 0

    def get_total_count(self):
        _total = 0
        for v in self.tracker.values():
            _total += v

        return _total
