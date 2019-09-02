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

ENV LANG C.UTF-8

# 安装软件
RUN set -eux \
    && apt-get update \
    && apt-get install -y --no-install-recommends cron wget unzip fontconfig \
        libfontenc1 libjpeg62-turbo libx11-6 libx11-data libxau6 libxcb1 \
        libxdmcp6 libxext6 libxfont1 libxrender1 x11-common xfonts-75dpi \
        xfonts-base xfonts-encodings xfonts-utils

WORKDIR /tmp

# 安装 Noto Sans SC 字体
RUN wget https://fonts.google.com/download?family=Noto%20Sans%20SC -O Noto_Sans_SC.zip \
    && unzip Noto_Sans_SC.zip -d /usr/share/fonts \
    && fc-cache 
# 安装 wkhtmltopdf
RUN wget https://downloads.wkhtmltopdf.org/0.12/0.12.5/wkhtmltox_0.12.5-1.stretch_amd64.deb -O wkhtmltox_0.12.5-1.stretch_amd64.deb \
    && dpkg -i wkhtmltox_0.12.5-1.stretch_amd64.deb 
        
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
    
# 清理无用的依赖包
RUN set -eux \
    && apt-get autoremove \
    && apt-get autoclean \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# 访问端口
EXPOSE 80

ARG APP_ENV=development
ENV APP_ENV ${APP_ENV}

COPY --chown=www-data:www-data . /var/www/html
COPY docker/000-default.conf /etc/apache2/sites-available/000-default.conf

WORKDIR /var/www/html
