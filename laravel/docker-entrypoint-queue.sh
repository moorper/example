#!/usr/bin/env bash

php artisan cache:clear
php artisan config:cache
php artisan route:cache
php artisan view:cache

# 加载调度任务并重启 cron
crontab docker/app.cron
/etc/init.d/cron restart

# 执行队列
php artisan queue:work --timeout=60
