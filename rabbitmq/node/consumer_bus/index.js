/**
 * Modules
 */
import amqp from 'amqplib/callback_api.js';
import { v4 } from 'uuid';

/**
 * Configure RabbitMQ server
 */
const RABBITMQ_DEFAULT_USER = process.env.RABBITMQ_DEFAULT_USER || 'user';
const RABBITMQ_DEFAULT_PASS = process.env.RABBITMQ_DEFAULT_PASS || 'password';
const RABBITMQ_SERVER = process.env.RABBITMQ_SERVER || 'localhost';
const RABBITMQ_PORT = process.env.RABBITMQ_PORT || 5672;
const RABBITMQ_CONNECTION_URI = `amqp://${RABBITMQ_DEFAULT_USER}:${RABBITMQ_DEFAULT_PASS}@${RABBITMQ_SERVER}:${RABBITMQ_PORT}`;


/**
 * Step 1
 * Connect to RabbitMQ server
 * дорога от 1 сервера к брокеру обмена сообщениями
 */
amqp.connect(RABBITMQ_CONNECTION_URI, {},
    async (errorConnect, connection) => {
        if (errorConnect) {
            console.error(errorConnect);
            process.exit(-1);
        }
        console.debug("connect RabbitMQ ok");

        /**
         * Step 2
         * Create RabbitMQ channel
         * полоса на дороге - для каждого типа машинки - сдлаем свою полосу
         * - Для автобусов, грузовиков, легковушек
         */
        await connection.createChannel(async (errorChannel, channel) => {
            if (errorChannel) {
                console.error(errorChannel);
                process.exit(-1);
            }
            console.debug("createChannel for bus RabbitMQ ok");

            /**
             * Step 3
             * Assert channel to queue
             */
            await channel.assertQueue('bus', { durable: true }, (errorQueue) => {
                if (errorQueue) {
                    console.error(errorQueue);
                    process.exit(-1);
                }
                console.debug("Bus queue asserted");

                /**
                 * Step 4
                 * Consumer
                 */
                channel.consume('bus', async (data) => {

                    /**
                     * Restore message from producer
                     */
                    const msgIn = JSON.parse(data.content.toString());
                    console.debug('Catch message:')
                    console.debug(msgIn);

                    channel.ack(data);
                })


            });
        })

})