class DataHandler:
    def __init__(self) -> None:
        self.locations = {}
        self.fuel_cost = {}

    def add_crab(self, location):
        try:
            self.locations[location]
        except KeyError:
            self.locations[location] = 1
        else:
            self.locations[location] += 1

    def move_location(self, location, new_location):
        _count = self.locations[location]
        
        # Account for fuel costs
        try:
            self.fuel_cost[new_location]
        except KeyError:
            self.fuel_cost[new_location] = abs(new_location - location) * _count
        else:
            self.fuel_cost[new_location] += abs(new_location - location) * _count

    def move_increasing_cost(self, location, new_location):
        _count = self.locations[location]
        _distance = abs(new_location - location)

        # Account for fuel costs
        _total = 0
        for i in range(1, _distance + 1):
            _total += _count * i
        try:
            self.fuel_cost[new_location]
        except KeyError:
            self.fuel_cost[new_location] = _total
        else:
            self.fuel_cost[new_location] += _total

    
    def get_min_location(self):
        return min(self.locations.keys())

    def get_max_location(self):
        return max(self.locations.keys())

    def get_distinct_locations(self):
        return set(self.locations.keys())



    def get_lowest_cost(self):
        return min(self.fuel_cost.values())
