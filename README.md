![](https://cdn.discordapp.com/attachments/846185616214589460/863076067358277672/n7AAAAAElFTkSuQmCC.png)
> ![](https://img.shields.io/static/v1?label=Built%20With&message=Java&style=for-the-badge&logo=java&color=orange) ![](https://img.shields.io/static/v1?label=Built%20With&message=BungeeCord&style=for-the-badge&logo=minecraft&color=yellow)

## What is FeatherQueue?
FeatherQueue is a player queueing and load balancing system for the Minecraft layer-7 proxy BungeeCord.

## What inspired FeatherQueue?
Here are a few reasons why FeatherQueue was created when solutions already exist:
* Most queueing and load balancing systems were/are proprietary.
* Solutions that are publicly available are often buggy in their support for player priorities and keeping the queue sorted.
* Solutions did not provide Developer API's.
* Solutions also failed to provide configurable plugin messages. 
* Existing solutions that do function appropriately require the deployment of an in memory cache or message broker. FeatherQueue requires neither of those.

## Features
- Support for game versions 1.7.x - 1.17.x
- Priority based queueing.
- Full configurable, add your own server's theme!
- Fully fledged Developer API.

## In-game Usage
| Command | Permission | Description |
| ----------- | ----------- | ----------- |
| /queue join [identifier] | queue.join | Join a queue. |
| /queue leave | queue.leave | Leave a queue. |
| /queue set size [identifier] [size] | queue.set.size | Set the maximum size of the queue. |
| /queue set status [identifier] [status] | queue.set.status | Set the status of a queue. |
| /queue set priority permission [identifier] [permission] | queue.set.priority.permission | Set the permission for a queue priority. |
| N/A | featherqueue.bypass | Bypass all queues, useful for administrators and youtubers! |

## Developer API
> This list is not exhaustive, but it contains what we find are some of the most powerful functions. Further exploration into classes can yield insight into more accessors and mutators that exist.

| Function | Return Type |
| ----------- | ----------- |
| `FeatherQueue#getQueueManager` | `QueueManager` |
| `FeatherQueue#getQueuePriorityManager` | `QueuePriorityManager` |
| `FeatherQueue#getQueuePlayerManager` | `QueuePlayerManager` |
| `QueueManager#getQueue(identifier: String)` | `Queue` |
| `QueuePriorityManager#getPriority(identifier: String)` | `QueuePriority` |
| `QueuePriorityManager#getPriorityForPlayer(player: ProxiedPlayer)` | `QueuePriority` |
| `QueuePlayerManager#getPlayer(target: ProxiedPlayer)` | `QueuePlayer` |
| `Queue#addPlayer(player: QueuePlayer)` | `boolean` |
| `Queue#removePlayer(player: QueuePlayer)` | `boolean` |
| `QueuePriority#getPermission` | `String` |
| `QueuePriority#getWeight` | `int` |
| `QueuePlayer#getPositionInQueue` | `int` |
| `QueuePlayer#getQueue` | `Queue` |
| `QueuePlayer#isQueued` | `boolean` |
| `QueuePlayer#getPriority` | `QueuePriority` |

| Event | Description |
| ----------- | ----------- |
| `PlayerQueueJoinEvent` | Fired when a player joins a queue. |
| `PlayerQueueLeaveEvent` | Fired when a player leaves a queue. |
| `PlayerQueuePushEvent` | Fired when a player is pushed to a queue's target server. |
| `QueueStatusChangeEvent` | Fired when a queue's status changes. |

## Contributors
* [Anmol Brar](https://www.github.com/brarsanmol)

## License
Copyright © 2021, Anmol Brar, All Rights Reserved.