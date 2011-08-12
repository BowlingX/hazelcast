/* 
 * Copyright (c) 2008-2010, Hazel Ltd. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.hazelcast.impl;

import com.hazelcast.monitor.LocalQueueOperationStats;
import com.hazelcast.monitor.LocalQueueStats;
import com.hazelcast.nio.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LocalQueueStatsImpl implements LocalQueueStats, DataSerializable {
    private int ownedItemCount;
    private int backupItemCount;
    private long minAge;
    private long maxAge;
    private long aveAge;

    private LocalQueueOperationStats queueOperationStats;

    public LocalQueueStatsImpl() {
    }

    public LocalQueueStatsImpl(int ownedItemCount, int backupItemCount, long minAge, long maxAge, long aveAge) {
        this.ownedItemCount = ownedItemCount;
        this.backupItemCount = backupItemCount;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.aveAge = aveAge;
    }

    public void writeData(DataOutput out) throws IOException {
        out.writeInt(ownedItemCount);
        out.writeInt(backupItemCount);
        out.writeLong(minAge);
        out.writeLong(maxAge);
        out.writeLong(aveAge);
        queueOperationStats.writeData(out);
    }

    public void readData(DataInput in) throws IOException {
        ownedItemCount = in.readInt();
        backupItemCount = in.readInt();
        minAge = in.readLong();
        maxAge = in.readLong();
        aveAge = in.readLong();
        queueOperationStats = new LocalQueueOperationStatsImpl();
        queueOperationStats.readData(in);
    }

    public int getOwnedItemCount() {
        return ownedItemCount;
    }

    public int getBackupItemCount() {
        return backupItemCount;
    }

    public long getMaxAge() {
        return maxAge;
    }

    public long getMinAge() {
        return minAge;
    }

    public long getAveAge() {
        return aveAge;
    }

    public LocalQueueOperationStats getOperationStats() {
        return queueOperationStats;
    }

    public void setOperationStats(LocalQueueOperationStats operationStats) {
        queueOperationStats = operationStats;
    }

    @Override
    public String toString() {
        return "LocalQueueStatsImpl{" +
                "aveAge=" + aveAge +
                ", ownedItemCount=" + ownedItemCount +
                ", backupItemCount=" + backupItemCount +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", queueOperationStats=" + queueOperationStats +
                '}';
    }
}