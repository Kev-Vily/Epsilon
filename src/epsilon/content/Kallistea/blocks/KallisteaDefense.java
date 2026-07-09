package epsilon.content.Kallistea.blocks;

import arc.graphics.Color;
import arc.math.Interp;
import arc.struct.Seq;
import epsilon.content.Kallistea.EpsFx;
import epsilon.content.Kallistea.EpsMusic;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.defense.*;
import epsilon.world.bullets.RingExplosionBullet;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.RegionPart;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawTurret;
import mindustry.world.meta.BuildVisibility;

import static mindustry.type.ItemStack.with;

public class KallisteaDefense {
    public static Block
            // walls
            gelionyteWall, quartzWall, quartzWallLarge,
            //region ganieris
            prism, testTurret,

            // region incers
            dispersive, sparkline, fluxray, gravitor, disruptor, pulsegrid, lancefield, expanse, nullflare;



    public static void load() {
        gelionyteWall = new Wall("gelionyte-wall"){{
            requirements(Category.defense, with(KallisteaItems.gelionyte, 6));
            health = 175;
            size = 1;
        }};
        quartzWall = new EpsShieldWall("quartz-wall"){{
           requirements(Category.defense, with(KallisteaItems.quartz, 12, KallisteaItems.calcite, 4));
           health = 840;
           size = 2;
           regenSpeed = 1.5f;
           glowColor = Color.cyan;
        }};
        quartzWallLarge = new EpsShieldWall("quartz-wall-large"){{
            requirements(Category.defense, with(KallisteaItems.quartz, 24, KallisteaItems.calcite, 12));
            health = 1440;
            size = 3;
            regenSpeed = 2.5f;
            shieldHealth = 900;
            glowColor = Color.cyan;
        }};
        dispersive = new EpsItemTurret("dispersive"){{
            requirements(Category.turret, with(KallisteaItems.calcite, 90, KallisteaItems.quartz, 50));
            squareSprite = false;
            health = 210;
            size = 3;
            itemCapacity = 20;
            targetAir = false;
            rotateSpeed = 2;
            reload = 155;
            range = 387;
            outlineColor = Color.valueOf("211c1c"); // Alt outline colors: "37272a", "241f1f".
            shootSound = EpsMusic.highShoot;

            drawer = new DrawTurret(){{
                basePrefix = "disbase-";
                parts.add(new RegionPart("-blade"){{
                    //x = 30f / 4f;
                    //y = 22f / 4f;
                    mirror = true;
                    under = true;
                    progress = PartProgress.warmup;
                    moveRot = -18;
                    moveX = 2;
                    moveY = -0.5f;
                    moves.add(new PartMove(PartProgress.recoil, -1, -1f, -18));
                }});
            }};

            shootY = -0.5f;
            shoot.firstShotDelay = 54;
            shoot.shots = 2;
            shoot.shotDelay = 10;
            moveWhileCharging = false;

            ammo(
                    KallisteaItems.quartz, new BasicBulletType(11,115){{
                        width = 6;
                        height = 16;
                        lifetime = 60;
                        frontColor = trailColor = Color.valueOf("effdff");
                        backColor = Color.valueOf("8b9cd3");
                        trailWidth = 1.8f;
                        trailLength = 10;
                        trailEffect = Fx.disperseTrail;
                        trailRotation = true;
                        trailInterval = 1.7f;
                        pierce = true;
                        pierceBuilding = true;
                        pierceCap = 2;

                        hitEffect = despawnEffect = new MultiEffect(
                                new ParticleEffect() {{
                                    lifetime = 20;
                                    length = 20;
                                    baseLength = 0;
                                    colorFrom = Color.valueOf("effdff");
                                    colorTo = Color.valueOf("8b9cd3");
                                    sizeFrom = 3;
                                    sizeTo = 0;
                                    cone = 40;
                                }},
                                new WaveEffect() {{
                                    sizeFrom = 0;
                                    sizeTo = 24;
                                    colorFrom = Color.valueOf("effdff");
                                    colorTo = Color.valueOf("effdff");
                                    lifetime = 9;
                                }}
                        );
                        shootEffect = new MultiEffect(
                                new ParticleEffect() {{
                                    lifetime = 16;
                                    length = 27;
                                    baseLength = 0;
                                    sizeFrom = 3.6f;
                                    sizeTo = 0;
                                    cone = 38;
                                    colorFrom = Color.valueOf("effdff");
                                    colorTo = Color.valueOf("8b9cd3");
                                }},
                                new ParticleEffect(){{
                                    offsetX = -28f / 4f;
                                    offsetY = -15f / 4f;
                                    useRotation = true;
                                    baseRotation = -45f;
                                    lifetime = 70;
                                    length = -40;
                                    baseLength = 0;
                                    sizeFrom = 2f;
                                    sizeTo = 0;
                                    cone = 12;
                                    colorFrom = Color.valueOf("ccccccb8");
                                    colorTo = Color.valueOf("6b6b6b97");
                                }},
                                new ParticleEffect(){{
                                    offsetX = -28f / 4f;
                                    offsetY = 15f / 4f;
                                    useRotation = true;
                                    baseRotation = 45f;
                                    lifetime = 70;
                                    length = -40;
                                    baseLength = 0;
                                    sizeFrom = 2f;
                                    sizeTo = 0;
                                    cone = 12;
                                    colorFrom = Color.valueOf("ccccccb8");
                                    colorTo = Color.valueOf("6b6b6b97");
                                }}
                        );
                        chargeEffect = new MultiEffect(
                                new ParticleEffect(){{
                                    useRotation = true;
                                    baseRotation = 0;
                                    cone = 20;
                                    startDelay = 0;
                                    rotWithParent = true;
                                    lifetime = 38;
                                    length = -34;
                                    baseLength = 34;
                                    colorFrom = Color.valueOf("effdff");
                                    colorTo = Color.valueOf("8b9cd3");
                                    sizeFrom = 0;
                                    sizeTo = 3;
                                }},
                                new ParticleEffect(){{
                                    useRotation = true;
                                    baseRotation = 90;
                                    cone = 20;
                                    startDelay = 15;
                                    rotWithParent = true;
                                    lifetime = 38;
                                    length = -34;
                                    baseLength = 34;
                                    colorFrom = Color.valueOf("effdff");
                                    colorTo = Color.valueOf("8b9cd3");
                                    sizeFrom = 0;
                                    sizeTo = 3;
                                }},
                                new ParticleEffect(){{
                                    useRotation = true;
                                    baseRotation = 180;
                                    cone = 20;
                                    startDelay = 25;
                                    rotWithParent = true;
                                    lifetime = 38;
                                    length = -34;
                                    baseLength = 34;
                                    colorFrom = Color.valueOf("effdff");
                                    colorTo = Color.valueOf("8b9cd3");
                                    sizeFrom = 0;
                                    sizeTo = 3;
                                }},
                                new ParticleEffect(){{
                                    useRotation = true;
                                    baseRotation = 270;
                                    cone = 20;
                                    startDelay = 30;
                                    rotWithParent = true;
                                    lifetime = 38;
                                    length = -34;
                                    baseLength = 34;
                                    colorFrom = Color.valueOf("effdff");
                                    colorTo = Color.valueOf("8b9cd3");
                                    sizeFrom = 0;
                                    sizeTo = 3;
                                }}
                        );

                        bulletInterval = 5.5f;
                        intervalRandomSpread = 20f;
                        intervalBullets = 2;
                        intervalAngle = 180f;
                        intervalSpread = 300f;
                        intervalBullet = new BasicBulletType(8, 30) {{
                            lifetime = 10;
                            width = 5;
                            height = 14;
                            frontColor = trailColor = Color.valueOf("effdff");
                            backColor = Color.valueOf("8b9cd3");
                            trailWidth = 1.6f;
                            trailLength = 6;

                            hitEffect = despawnEffect = new ParticleEffect() {{
                                lifetime = 20;
                                length = 27;
                                baseLength = 0;
                                colorFrom = Color.valueOf("effdff");
                                colorTo = Color.valueOf("8b9cd3");
                                sizeFrom = 2.6f;
                                sizeTo = 0;
                                cone = 40;
                            }};
                        }};

                        fragBullets = 3;
                        fragRandomSpread = 45;
                        fragAngle = 180;
                        fragSpread = 45;
                        fragBullet = new BasicBulletType(8, 45){{
                            lifetime = 10;
                            width = 5;
                            height = 14;
                            frontColor = trailColor = Color.valueOf("effdff");
                            backColor = Color.valueOf("8b9cd3");
                            trailWidth = 1.6f;
                            trailLength = 6;

                            hitEffect = despawnEffect = new ParticleEffect() {{
                                lifetime = 20;
                                length = 27;
                                baseLength = 0;
                                colorFrom = Color.valueOf("effdff");
                                colorTo = Color.valueOf("8b9cd3");
                                sizeFrom = 2.6f;
                                sizeTo = 0;
                                cone = 40;
                            }};
                        }};
                    }}
            );
        }};

        /*prism = new EpsPowerTurret("prism"){{
           requirements(Category.turret, with(KallisteaItems.quartz, 120, KallisteaItems.fylion, 85));
            size = 2;
            health = 260;
            range = 160f;
            reload = 45f;
            rotateSpeed = 6f;
            targetAir = true;
            targetGround = true;
            consumePower(1.2f);
            coolant = consumeCoolant(0.2f);
            shootEffect = Fx.shootSmokeSquare;
            shootSound = Sounds.laser;
            shootType = new LaserBulletType() {{
                damage = 50f;
                length = 160f;
                lifetime = 22f;
                width = 6f;
                colors = new Color[]{Color.valueOf("d580ff"), Color.white};
                hitEffect = EpsFx.prismBurst;
                despawnEffect = EpsFx.prismBurst;
                drawSize = 420f;
                fragBullets = 6;
                fragAngle = 360f;
                fragVelocityMin = 0.9f;
                fragVelocityMax = 1.1f;
                fragBullet = new BasicBulletType(){{
                    keepVelocity = true;
                    damage = 90;
                    hitEffect = EpsFx.prismBurst;
                    despawnEffect = EpsFx.prismBurst;
                }};
            }};
        }};*/

        //region Incers


        disruptor = new EpsItemTurret("distruptor") {{
            requirements(Category.turret, with(KallisteaItems.gelionyte, 1));
            ammo(
                    KallisteaItems.gelionyte, new BasicBulletType(2.3f, 150, "epsilon-plasm-bullet"){{
                        width = 29f;
                        height = 34f;
                        smokeEffect = Fx.shootBigSmoke;
                        lifetime = 170;
                        ammoMultiplier = 1;
                        hitColor = backColor = Color.valueOf("8b1d2a");
                        frontColor = trailColor = Color.valueOf("ffb3bc");
                        trailWidth = 4f;
                        trailLength = 13;
                        splashDamage = 450;
                        splashDamageRadius = 73;
                        makeFire = true;
                        hitEffect = despawnEffect = new MultiEffect(new ExplosionEffect(){{
                            lifetime = 150;
                            waveStroke = 5;
                            waveLife = 30;
                            waveColor = Color.valueOf("ffb5bc");
                            sparkColor = Color.valueOf("ffb3bc");
                            waveRad = 70;
                            smokeSize = 13;
                            smokes = 14;
                            smokeRad = 80;
                            smokeColor = Color.valueOf("ffb3bc");
                            smokeSizeBase = 0;
                            sparks = 12;
                            sparkRad = 50;
                            sparkLen = 8;
                            sparkStroke = 2f;
                        }},
                                new WaveEffect() {{
                                    lifetime = 30f;
                                    colorFrom = Color.valueOf("ffb3bc");
                                    colorTo = Color.valueOf("ffb5bc");
                                    sizeFrom = 72;
                                    sizeTo = 72;
                                    lightScl = 3;
                                    lightOpacity = 1;
                                    strokeFrom = 6f;
                                    strokeTo = 0;
                                }},
                                new ParticleEffect(){{
                                    colorFrom = Color.valueOf("ffb5bc");
                                    colorTo = Color.valueOf("ffb3bc");
                                    particles = 24;
                                    cone = 360;
                                    baseLength = 80;
                                    lightScl = 3;
                                    lightOpacity = 2;
                                    spin = 0;
                                    sizeFrom = 5.4f;
                                    sizeTo = 0;
                                    offset = 1;
                                }});
                        buildingDamageMultiplier = 0.3f;
                        chargeEffect = new MultiEffect(EpsFx.plasmaChargeRed, new WaveEffect(){{
                            rotWithParent = true;
                            followParent = true;
                            lifetime = 85;
                            sizeFrom = 35;
                            sizeTo = 0;
                            strokeFrom = 0;
                            strokeTo = 2;
                            colorFrom = Color.valueOf("8b1d2a");
                            colorTo = Color.valueOf("ffb3bc");
                        }},
                                new WaveEffect(){{
                                    rotWithParent = true;
                                    followParent = true;
                                    lifetime = 150;
                                    sizeFrom = 32;
                                    sizeTo = 0;
                                    strokeFrom = 0;
                                    strokeTo = 2;
                                    colorFrom = Color.valueOf("8b1d2a");
                                    colorTo = Color.valueOf("ffb3bc");
                                }});

                        intervalBullets = 2;
                        bulletInterval = 6;
                        intervalSpread = 0;
                        intervalRandomSpread = 45;
                        intervalBullet = new BasicBulletType(3, 20){{
                            width = 9f;
                            height = 13f;
                            lifetime = 1;
                            hitColor = backColor = trailColor = Color.valueOf("8b1d2a");
                            frontColor = Color.valueOf("ffb3bc");
                            trailWidth = 1.8f;
                            trailLength = 5;
                            buildingDamageMultiplier = 0.3f;
                            hitEffect = despawnEffect = new ParticleEffect(){{
                                lifetime = 36f;
                                colorFrom = Color.valueOf("ffb3bc");
                                colorTo = Color.valueOf("ffb3bc");
                                particles = 4;
                                cone = 45;
                                length = 19;
                                baseLength = 2;
                                spin = 0;
                                sizeFrom = 4.9f;
                                sizeTo = 0;
                                offset = 1;
                            }};
                        }};
                    }});

            shoot.firstShotDelay = 150;
            moveWhileCharging = false;
            size = 5;
            recoil = 1.6f;
            shake = 25f;
            reload = 320;
            range = 294;
        }};

        lancefield = new EpsPowerTurret("lancefield"){{
            requirements(Category.turret, with(KallisteaItems.quartz, 1));
            size = 6;
            health = 1650;
            fraction = "Incers";
            consumePower(390f);
            recoil = 2;
            shake = 11f;
            reload = 180;
            range = 360;
            shoot.shots = 6;
            shoot.shotDelay = 20;
            shoot.firstShotDelay = 180;
            moveWhileCharging = false;
            shootType = new BasicBulletType(){{
                width = 15f;
                height = 20f;
                smokeEffect = Fx.shootBigSmoke;
                lifetime = 90;
                speed = 3.5f;
                ammoMultiplier = 1;
                hitColor = backColor = Color.valueOf("633163");
                frontColor = trailColor = Color.valueOf("966596");
                trailWidth = 5f;
                trailLength = 17;
                splashDamage = 450;
                splashDamageRadius = 73;
                chargeEffect = new MultiEffect(
                        EpsFx.energyOverloadPulse,
                  EpsFx.purpleOrbital,
                  EpsFx.plasmaChargePurple,
                        new WaveEffect(){{
                            rotWithParent = true;
                            followParent = true;
                            lifetime = 150;
                            sizeFrom = 32;
                            sizeTo = 0;
                            strokeFrom = 0;
                            strokeTo = 2;
                            colorFrom = Color.valueOf("c075d1");
                            colorTo = Color.valueOf("9b74a3");
                        }}
                );
                intervalBullets = 2;
                bulletInterval = 6;
                intervalSpread = 0;
                intervalRandomSpread = 45;
                intervalBullet = new BasicBulletType(3, 0){{
                    width = 9f;
                    height = 13f;
                    lifetime = 1;
                    hitColor = backColor = trailColor = Color.valueOf("8b1d2a");
                    frontColor = Color.valueOf("e9b3ff");
                    trailWidth = 1.8f;
                    trailLength = 8;
                    buildingDamageMultiplier = 0.3f;
                    hitEffect = despawnEffect = new ParticleEffect(){{
                        lifetime = 36f;
                        colorFrom = Color.valueOf("e9b3ff");
                        colorTo = Color.valueOf("e9b3ff");
                        particles = 4;
                        cone = 45;
                        length = 19;
                        baseLength = 2;
                        spin = 0;
                        sizeFrom = 3.9f;
                        sizeTo = 0;
                        offset = 1;
                    }};
                }};
            }};
        }};
        // I want to make it look like The Orbital Strike Cannon from Unstable SMPa
        /*nullflare = new EpsItemTurret("nullflare"){{
           requirements(Category.turret, with(KallisteaItems.magnetite, 950, KallisteaItems.fylion, 1350, KallisteaItems.tantalum, 900, KallisteaItems.anveiur, 640));
           size = 6;
           health = 4500;
           fraction = "Incers";
           consumePower(390f);
           recoil = 2;
           shake = 11f;
           reload = 180;
           range = 1080;
           shoot.firstShotDelay = 300;
           moveWhileCharging = false;
           ammo(
             KallisteaItems.tantalum, new BasicBulletType(10, 400){{
               width = 5;
               height = 5;
               smokeEffect = Fx.shootBigSmoke;
               lifetime = 100;
               speed = 5f;
               ammoMultiplier = 1;
               hitColor = backColor = Color.valueOf("633163");
               frontColor = trailColor = Color.valueOf("966596");
               trailWidth = 5f;
               trailLength = 17;
               splashDamage = 450;
               splashDamageRadius = 73;
               fragAngle = 360;
               fragBullets = 12;
               fragBullet = new BasicBulletType(650, 4){{
                   width = 4;
                   height = 4;
                   lifetime = 60;
                   speed = 1.5f;
                   hitEffect = Fx.blastExplosion;
                   fragAngle = 135;
                   fragBullets = 5;
                   fragBullet = new BasicBulletType(350, 4){{
                       width = 4;
                       height = 4;
                       lifetime = 60;
                       speed = 1.5f;
                       hitEffect = Fx.blastExplosion;
                      fragAngle = 180;
                      fragBullets = 7;
                      fragBullet = new BasicBulletType(150, 4){{
                          width = 4;
                          height = 4;
                          lifetime = 60;
                          speed = 1.5f;
                          hitEffect = Fx.blastExplosion;
                          fragAngle = 215;
                          fragBullets = 9;
                          fragBullet = new BasicBulletType(95, 4){{
                              width = 4;
                              height = 4;
                              lifetime = 60;
                              speed = 1.5f;
                              hitEffect = Fx.blastExplosion;
                          }};
                      }};
                   }};
               }};
             }}
           );
        }};*/

        testTurret = new EpsItemTurret("teleport-turret"){{
            requirements(Category.turret, with(
                    Items.copper, 300,
                    Items.silicon, 200
            ));

            ammo(
                    Items.surgeAlloy,
                    new RingExplosionBullet(){{
                        rings = 4;
                        bulletsPerRing = 32;
                        ringBullet = new BasicBulletType(1.5f, 50f){{
                                lifetime = 25f;

                                splashDamage = 120f;
                                splashDamageRadius = 32f;

                                hitEffect = Fx.blastExplosion;
                                despawnEffect = Fx.blastExplosion;

                                fragBullets = 0;
                            }};
                    }}
            );
        }};
    }
}

