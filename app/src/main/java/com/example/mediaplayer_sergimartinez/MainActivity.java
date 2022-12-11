package com.example.mediaplayer_sergimartinez;

import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;



public class MainActivity<MediaObserver> extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] tiposmusica = {"Musica RAW Directo", "Musica URI Raw", "VIDEO"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tiposmusica);
        Spinner tipoDeMusica = (Spinner) findViewById(R.id.spinner);
        progressBar = findViewById(R.id.progressBar);

        tipoDeMusica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Spinner tiposMusica = (Spinner) findViewById(R.id.spinner);
                String musicaSeleccionada = (String) tiposMusica.getSelectedItem().toString();


                Button botPlay = findViewById(R.id.btplay);
                Button botPau = findViewById(R.id.btpause);
                Button botStop = findViewById(R.id.btstop);

                Button btmenos = findViewById(R.id.btmenos);
                Button btmas = findViewById(R.id.btmas);

                TextView tvmeta = findViewById(R.id.tvmeta);
                TextView tvmeta2 = findViewById(R.id.tvmeta3);
                TextView tvmeta3 = findViewById(R.id.tvmeta4);

                if (musicaSeleccionada.equals("Musica URI Raw")) {
                    tvmeta.setText("SELECCIONADO URI RAW");

                    Uri myUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.jojo);
                    MediaMetadataRetriever m_meta = new MediaMetadataRetriever();
                    m_meta.setDataSource(MainActivity.this, myUri);

                    String titulo = m_meta.extractMetadata(7);
                    String duracion = m_meta.extractMetadata(9);

                    tvmeta2.setText("Titulo de la cancion: " + titulo);
                    tvmeta3.setText("Duracion de la cancion: " + duracion + " milisegundos");

                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


                    try {
                        mediaPlayer.setDataSource(getApplicationContext(), myUri);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    btmas.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick (View view) {
                            int currentPosition = mediaPlayer.getCurrentPosition();
                            int videoDuration = mediaPlayer.getDuration();

                            int msec = currentPosition + 10000;
                            mediaPlayer.seekTo(msec);
                        }
                    });

                    btmenos.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick (View view) {
                            int currentPosition = mediaPlayer.getCurrentPosition();
                            int videoDuration = mediaPlayer.getDuration();

                            int msec = currentPosition - 10000;
                            mediaPlayer.seekTo(msec);
                        }
                    });

                    botPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mediaPlayer.start();
                            tvmeta.setText("REPRODUCIENDO...");
                        }
                    });

                    botPau.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mediaPlayer.pause();
                            tvmeta.setText("CANCION EN PAUSA");
                        }
                    });

                    botStop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mediaPlayer.stop();
                            tvmeta.setText("CANCION PARADA");
                            try {
                                mediaPlayer.prepare();
                            } catch (Exception e) {
                            }
                        }
                    });

                } else if (musicaSeleccionada.equals("Musica RAW Directo")) {
                    tvmeta.setText("SELECCIONADO RAW");

                    MediaPlayer mediaPlayer2;
                    mediaPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.titan);



                    Uri myUri2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.titan);
                    MediaMetadataRetriever m_meta = new MediaMetadataRetriever();
                    m_meta.setDataSource(MainActivity.this, myUri2);

                    String titulo = m_meta.extractMetadata(7);
                    String duracion = m_meta.extractMetadata(9);

                    tvmeta2.setText("Titulo de la cancion: " + titulo);
                    tvmeta3.setText("Duracion de la cancion: " + duracion + " milisegundos");

                    btmas.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick (View view) {
                            int currentPosition = mediaPlayer2.getCurrentPosition();
                            int videoDuration = mediaPlayer2.getDuration();

                            int msec = currentPosition + 10000;
                            mediaPlayer2.seekTo(msec);
                        }
                    });

                    btmenos.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick (View view) {
                            int currentPosition = mediaPlayer2.getCurrentPosition();
                            int videoDuration = mediaPlayer2.getDuration();

                            int msec = currentPosition - 10000;
                            mediaPlayer2.seekTo(msec);
                        }
                    });
                    botPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mediaPlayer2.start();
                            tvmeta.setText("REPRODUCIENDO...");
                        }
                    });

                    botPau.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mediaPlayer2.pause();
                            tvmeta.setText("CANCION EN PAUSA");
                        }
                    });

                    botStop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mediaPlayer2.stop();
                            tvmeta.setText("CANCION PARADA");
                            try {
                                mediaPlayer2.prepare();
                            } catch (Exception e) {
                            }
                        }
                    });
                } else if (musicaSeleccionada.equals("VIDEO")) {
                    tvmeta.setText("SELECCIONADO VIDEO");
                    VideoView video = (VideoView) findViewById(R.id.videoView);
                    video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.capibara));

                    MediaController mediaController = new MediaController(MainActivity.this);
                    mediaController.setAnchorView(video);
                    video.setMediaController(mediaController);


                    Uri myUri3 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.capibara);
                    MediaMetadataRetriever m_meta = new MediaMetadataRetriever();
                    m_meta.setDataSource(MainActivity.this, myUri3);

                    String titulo = m_meta.extractMetadata(7);
                    String duracion = m_meta.extractMetadata(9);

                    tvmeta2.setText("Titulo de la cancion: " + titulo);
                    tvmeta3.setText("Duracion de la cancion: " + duracion + " milisegundos");



                    btmas.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick (View view) {
                            video.canSeekForward();
                            int currentPosition = video.getCurrentPosition();
                            int videoDuration = video.getDuration();

                            int msec = currentPosition + 10000;
                            video.seekTo(msec);
                        }
                    });

                    btmenos.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick (View view) {
                            video.canSeekBackward();
                            int currentPosition = video.getCurrentPosition();
                            int videoDuration = video.getDuration();

                            int msec = currentPosition - 10000;
                            video.seekTo(msec);
                        }
                    });


                    botPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            video.start();
                            tvmeta.setText("REPRODUCIENDO...");
                        }
                    });

                    botPau.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            video.pause();
                            tvmeta.setText("VIDEO PAUSADO");
                        }
                    });
                    botStop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                                    }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        tipoDeMusica.setAdapter(adapter);
    }
}