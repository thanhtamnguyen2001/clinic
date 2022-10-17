import { useState, useEffect } from 'react';

import UserService from '~/services/user.service';

const BoardNurse = () => {
    const [content, setContent] = useState('');

    useEffect(() => {
        UserService.getNurseBoard().then(
            (response) => {
                setContent(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data && error.response.data.message) ||
                    error.message ||
                    error.toString();

                setContent(_content);
            },
        );
    }, []);

    return <h1>{content}</h1>;
};

export default BoardNurse;
