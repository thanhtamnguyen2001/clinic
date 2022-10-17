import classNames from 'classnames/bind';
import React from 'react';
import { Carousel } from 'react-bootstrap';
import styles from './Banner.module.scss';

const cx = classNames.bind(styles);
const listCarousel = [
    {
        id: 1,
        image: 'https://wallpaperaccess.com/full/1282794.jpg',
        title: '1',
        content: 'Chao',
    },
    {
        id: 2,
        image: 'https://tophinhanhdep.com/wp-content/uploads/2021/11/4K-Medical-Wallpapers.jpg',
        title: '2',
        content: 'Chao',
    },
    {
        id: 3,
        image: 'https://wallpaperaccess.com/full/960588.jpg',
        title: '3',
        content: 'Chao',
    },
    {
        id: 4,
        image: 'https://wallpaperaccess.com/full/1330652.jpg',
        title: '4',
        content: 'Chao',
    },
];

const Banner = () => {
    return (
        <Carousel fade controls={false}>
            {listCarousel.map((item, idx) => {
                return (
                    <Carousel.Item key={idx}>
                        <img className={cx('banner-img')} src={item.image} alt={`Slide ${item.id}`} />
                        <Carousel.Caption>
                            <h3>{item.title}</h3>
                            <p>{item.content}</p>
                        </Carousel.Caption>
                    </Carousel.Item>
                );
            })}
        </Carousel>
    );
};

export default Banner;
