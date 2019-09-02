FROM php:7.3-apache

LABEL maintainer="liuzhaowei55@gmail.com"

# 设置时区
ARG TZ=Asia/Shanghai
ENV TZ ${TZ}
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 设置软件源
ARG DEBIAN_URL=mirrors.ustc.edu.cn
ENV DEBIAN_URL ${DEBIAN_URL}
RUN sed -i "s/deb.debian.org/$DEBIAN_URL/g" /etc/apt/sources.list

ARG DEBIAN_SECURITY_URL=mirrors.ustc.edu.cn/debian-security
ENV DEBIAN_SECURITY_URL ${DEBIAN_SECURITY_URL}
RUN sed -i "s|security.debian.org/debian-security|$DEBIAN_SECURITY_URL|g" /etc/apt/sources.list

# 安装软件 cron
RUN set -eux \
        && apt-get update \
        && apt-get install -y --no-install-recommends cron \
        && apt-get autoremove \
        && apt-get autoclean \
        && apt-get clean \
        && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*
        
# 安装拓展
ENV PHP_EXTENSION \
    pdo_mysql \
    bcmath
ENV PECL_EXTENSION \
    redis
RUN docker-php-ext-install $PHP_EXTENSION \
    && pecl install $PECL_EXTENSION \
    && docker-php-ext-enable $PECL_EXTENSION \
                             opcache \
    && a2enmod rewrite
    
# 访问端口
EXPOSE 80

ARG APP_ENV=development
ENV APP_ENV ${APP_ENV}

COPY --chown=www-data:www-data . /var/www/html
COPY docker/000-default.conf /etc/apache2/sites-available/000-default.conf

WORKDIR /var/www/html
