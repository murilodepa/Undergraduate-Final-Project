import React from "react";
import LottieView from 'lottie-react-native'
import { Container } from './styles'
import { useNavigation } from "@react-navigation/native";

export function Splash() {

    const navigation = useNavigation()

    return (
        <Container>
           <LottieView 
                source={require('../../assets/store-splash.json')} 
                autoPlay 
                speed={0.6}
                loop={false}
                onAnimationFinish={() => navigation.navigate('Capture')}
            /> 
        </Container>
    )
}