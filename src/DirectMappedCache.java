public class DirectMappedCache extends Cache {

    /**
     * Create a new Cache with a positive capacity and backing store
     * as a file located at filePath.
     *
     * @param capacity a positive integer capacity of the cache
     * @param filePath the file path to the file acting as backing store.
     */
    public DirectMappedCache(int capacity, String filePath) {
        super(capacity, filePath);
    }

    @Override
    protected int findEvictCandidate(int inKey) {
        return inKey % capacity;
    }


    /**
     * Evict data from the cache to make room for incoming data
     * whose key is the argument inKey.
     * The data to be evicted is the item, if one exists,
     * inhabiting the index where the incoming data should go.
     * Returns the index of the evicted data,
     * and thus the index of where the incoming data should be installed.
     * If the incoming key is already in the cache,
     * no data is evicted and -1 is returned.
     *
     * @param inKey the incoming key to be installed
     * @return the index where data was evicted or -1
     */
    @Override
    public int evictData(int inKey) {
        int index = findEvictCandidate(inKey);
        if (this.data[index] != null && this.data[index].getKey() != inKey) {
            this.data[index] = null;
            size--;
            return index;
        }

        return -1;
    }

    /**
     * Install the key-data pair into the cache as a CacheItem,
     * evicting a previously accessed cache item if necessary.
     * A CacheItem is installed in the index equal
     * to the key modulo the cache capacity.
     * Returns the index in which the CacheItem was stored.
     *
     * @param key the key of the data to install in the cache.
     * @param data the data to install in the cache.
     * @return the index in the cache where the CacheItem is installed.
     */
    protected int installData(int key, int data) {
        int insertIndex = evictData(key);
        if (insertIndex == -1) {
            insertIndex = findEvictCandidate(key);
        }
        this.data[insertIndex] = new CacheItem(key, data);
        this.size++;
        return insertIndex;
    }

}
