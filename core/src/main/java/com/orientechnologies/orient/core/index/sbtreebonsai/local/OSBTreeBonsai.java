package com.orientechnologies.orient.core.index.sbtreebonsai.local;

import java.util.Collection;
import java.util.Map;

import com.orientechnologies.common.serialization.types.OBinarySerializer;
import com.orientechnologies.orient.core.db.record.ridbag.sbtree.OSBTreeRidBag;
import com.orientechnologies.orient.core.index.sbtree.OTreeInternal;
import com.orientechnologies.orient.core.storage.impl.local.OStorageLocalAbstract;

/**
 * @author <a href="mailto:enisher@gmail.com">Artem Orobets</a>
 */
public interface OSBTreeBonsai<K, V> extends OTreeInternal<K, V> {
  void create(String name, OBinarySerializer<K> keySerializer, OBinarySerializer<V> valueSerializer);

  void create(long fileId, OBinarySerializer<K> keySerializer, OBinarySerializer<V> valueSerializer);

  String getName();

  long getFileId();

  OBonsaiBucketPointer getRootBucketPointer();

  V get(K key);

  boolean put(K key, V value);

  void close(boolean flush);

  void close();

  void clear();

  void delete();

  void load(long fileId, OBonsaiBucketPointer rootBucketPointer, OStorageLocalAbstract storageLocal);

  long size();

  V remove(K key);

  Collection<V> getValuesMinor(K key, boolean inclusive, int maxValuesToFetch);

  void loadEntriesMinor(K key, boolean inclusive, RangeResultListener<K, V> listener);

  Collection<V> getValuesMajor(K key, boolean inclusive, int maxValuesToFetch);

  void loadEntriesMajor(K key, boolean inclusive, RangeResultListener<K, V> listener);

  Collection<V> getValuesBetween(K keyFrom, boolean fromInclusive, K keyTo, boolean toInclusive, int maxValuesToFetch);

  K firstKey();

  K lastKey();

  void loadEntriesBetween(K keyFrom, boolean fromInclusive, K keyTo, boolean toInclusive, RangeResultListener<K, V> listener);

  void flush();

  int getRealBagSize(Map<K, OSBTreeRidBag.Change> changes);
}
