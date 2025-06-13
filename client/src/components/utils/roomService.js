import { api } from "./axiosWrap";

export async function createRoom(photo, roomType, roomPrice) {
    try {
        const formData = new FormData();
        formData.append("photo", photo);
        formData.append("roomType", roomType);
        formData.append("roomPrice", roomPrice);
        
        const response = await api.post("/rooms", formData);

        console.log(response);

        return response.status === 201;
    } catch (ex) {
        throw new Error("Error on creating room: " + ex.message);
    }
}

export async function getRoomTypes() {
    try {
        const response = await api.get("rooms/types");

        return response.data;
    } catch (ex) {
        throw new Error("Error on fetching room type: " + ex.message);
    }
}