/*
 * Copyright (C) 2022 Dremio
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
 */
package org.projectnessie.versioned.storage.common.logic;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.immutables.value.Value;
import org.projectnessie.versioned.storage.common.indexes.StoreKey;
import org.projectnessie.versioned.storage.common.persist.ObjId;

@Value.Immutable
public interface DiffEntry {
  @Value.Parameter(order = 1)
  StoreKey key();

  @Value.Parameter(order = 2)
  @Nullable
  @jakarta.annotation.Nullable
  ObjId fromId();

  @Value.Parameter(order = 3)
  int fromPayload();

  @Value.Parameter(order = 4)
  @Nullable
  @jakarta.annotation.Nullable
  UUID fromContentId();

  @Value.Parameter(order = 5)
  @Nullable
  @jakarta.annotation.Nullable
  ObjId toId();

  @Value.Parameter(order = 6)
  int toPayload();

  @Value.Parameter(order = 7)
  @Nullable
  @jakarta.annotation.Nullable
  UUID toContentId();

  @Nonnull
  @jakarta.annotation.Nonnull
  static DiffEntry diffEntry(
      @Nonnull @jakarta.annotation.Nonnull StoreKey key,
      @Nullable @jakarta.annotation.Nullable ObjId fromId,
      int fromPayload,
      @Nullable @jakarta.annotation.Nullable UUID fromContentId,
      @Nullable @jakarta.annotation.Nullable ObjId toId,
      int toPayload,
      @Nullable @jakarta.annotation.Nullable UUID toContentId) {
    checkArgument(
        (fromId == null && fromPayload == 0 && fromContentId == null)
            || (fromId != null && fromPayload >= 0 && fromPayload <= 127));
    checkArgument(
        (toId == null && toPayload == 0 && toContentId == null)
            || (toId != null && toPayload >= 0 && toPayload <= 127));
    return ImmutableDiffEntry.of(
        key, fromId, fromPayload, fromContentId, toId, toPayload, toContentId);
  }
}
