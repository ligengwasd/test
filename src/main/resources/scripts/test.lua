local hongBao = redis.call('rpop', KEYS[1]);
redis.log(redis.LOG_WARNING, 1111);
return hongBao;