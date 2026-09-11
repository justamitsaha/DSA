"""
Structure:
Index [0] -> [ Key1 | Val1 | Next ] -> [ Key4 | Val4 | Next ]
Index [1] -> null
Index [2] -> [ Key2 | Val2 | Next ]
Index [3] -> [ Key3 | Val3 | Next ]
"""

class Entry:
    def __init__(self, key, value):
        self.key = key
        self.value = value


class HashMapCustom:
    """Custom HashMap Implementation using Chaining."""
    def __init__(self, capacity=16):
        self.capacity = capacity
        self.table = [[] for _ in range(capacity)]
        self.size = 0
        self.load_factor = 0.75

    def _hash(self, key):
        return hash(key) % self.capacity

    def put(self, key, value):
        if self.size / self.capacity >= self.load_factor:
            self._rehash()

        index = self._hash(key)
        bucket = self.table[index]
        
        for entry in bucket:
            if entry.key == key:
                entry.value = value
                return

        bucket.append(Entry(key, value))
        self.size += 1

    def get(self, key):
        index = self._hash(key)
        bucket = self.table[index]
        for entry in bucket:
            if entry.key == key:
                return entry.value
        return None

    def remove(self, key):
        index = self._hash(key)
        bucket = self.table[index]
        for i, entry in enumerate(bucket):
            if entry.key == key:
                bucket.pop(i)
                self.size -= 1
                return True
        return False

    def _rehash(self):
        old_table = self.table
        self.capacity *= 2
        self.table = [[] for _ in range(self.capacity)]
        self.size = 0
        
        for bucket in old_table:
            for entry in bucket:
                self.put(entry.key, entry.value)

    def __len__(self):
        return self.size


if __name__ == "__main__":
    hmap = HashMapCustom()
    hmap.put("One", 1)
    hmap.put("Two", 2)
    hmap.put("Three", 3)
    
    print("Get One:", hmap.get("One"))
    print("Get Two:", hmap.get("Two"))
    
    hmap.put("One", 11)
    print("Get One (updated):", hmap.get("One"))
    
    hmap.remove("Two")
    print("Get Two (after remove):", hmap.get("Two"))
    print("Size:", len(hmap))
