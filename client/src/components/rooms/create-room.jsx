import { useEffect, useState } from "react";
import { createRoom } from "../utils/roomService";

function CreateRoom() {
    const [newRoom, setNewRoom] = useState({
        photo: null,
        roomType: null,
        roomPrice: null
    });
    const [photoPreview, setPhotoPreview] = useState(null);
    const [successMessage, setSuccessMessage] = useState(null);
    const [errorMessage, setErrorMessage] = useState(null);
    
    useEffect(() => {
        setSuccessMessage(null);
    }, [errorMessage]);

    useEffect(() => {
        setErrorMessage(null);
    }, [successMessage])

    function handleRoomInputChange(e) {
        const name = e.target.name;
        let val = e.target.value;

        if (name === "roomPrice" && !isNaN(val)) {
            val = Number(val);
        } else {
            val = null;
        }

        setNewRoom({...newRoom, [name]: val});
    }

    function handleSelectPhoto(e) {
        const val = e.target.files[0];
        setNewRoom({...newRoom, photo: val});
        setPhotoPreview(URL.createObjectURL(val));
    }

    async function handleSubmit(e) {
        try {
            const response = await createRoom(newRoom.photo, newRoom.roomType, newRoom.roomPrice);

            console.log(response);

            if (response !== undefined) {
                setSuccessMessage("A new room was added");
                setNewRoom({
                    photo: null,
                    roomType: null,
                    roomPrice: null
                });
                setPhotoPreview(null);
            } else {
                setErrorMessage("Error adding room");
            }


        } catch (ex) {
            setErrorMessage(ex.message);
        }
    }

    return (
        <>

        </>
    );
}

export default CreateRoom;