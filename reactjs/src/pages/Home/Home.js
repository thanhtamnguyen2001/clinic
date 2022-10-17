import { useState } from 'react';
import Banner from '~/components/Banner';
import Specialty from '~/components/Specialty';
import request from '~/utils/httpRequest';

function Home() {
    return (
        <div>
            <Banner />
            <h1>Home page</h1>
            <Specialty />
        </div>
    );
}

export default Home;
