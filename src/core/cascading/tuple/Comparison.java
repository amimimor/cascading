/*
 * Copyright (c) 2007-2010 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Cascading is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cascading is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cascading.  If not, see <http://www.gnu.org/licenses/>.
 */

package cascading.tuple;

import java.util.Comparator;

/**
 * The Comparison interface allows specific underlying type mechanisms to additionally return relevant
 * {@link Comparator} implementations when required internally.
 * <p/>
 * In the case of Hadoop, {@link org.apache.hadoop.io.serializer.Serialization} implementations that present
 * alternative serialization implementations for custom types manged by {@link Tuple}s should also
 * implement the {@link #getComparator(Class)} method.
 * <p/>
 * During runtime Cascading can identify and use the correct Comparator during grouping operations if it was
 * not given explicitly on the {@link Fields#setComparator(Comparable, java.util.Comparator)} family of methods.
 * <p/>
 * See the class {@link cascading.tuple.hadoop.BytesSerialization} for an example.
 * <p/>
 * see cascading.tuple.hadoop.BytesSerialization
 */
public interface Comparison<T>
  {
  Comparator<T> getComparator( Class<T> type );
  }