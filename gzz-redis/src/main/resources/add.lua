local lockKey = key[1]
local lockValue = key[2]

-- setnx info
local result_1 = redis.call('SETNX', lockKey, lockValue)
if result_1 == true
then
local result_2 = redis.call('SETEX', lockKey,3600, lockValue)
return result_1
else
return result_1
end

