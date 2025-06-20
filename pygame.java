import pygame
import random
import sys

# Initialize Pygame
pygame.init()

# Screen setup
WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Gun Shooting Game")

# Colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)

# Clock
clock = pygame.time.Clock()
FPS = 60

# Player
player_width, player_height = 50, 60
player_x = 100
player_y = HEIGHT // 2 - player_height // 2
player_speed = 5

# Bullet
bullet_speed = 10
bullets = []

# Enemy
enemy_width, enemy_height = 40, 40
enemies = []

def spawn_enemy():
    y = random.randint(0, HEIGHT - enemy_height)
    enemies.append(pygame.Rect(WIDTH, y, enemy_width, enemy_height))

enemy_spawn_event = pygame.USEREVENT + 1
pygame.time.set_timer(enemy_spawn_event, 1500)  # Spawn every 1.5 seconds

# Game loop
running = True
while running:
    clock.tick(FPS)
    screen.fill(WHITE)

    # Event handling
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == enemy_spawn_event:
            spawn_enemy()
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                bullet = pygame.Rect(player_x + player_width, player_y + player_height // 2 - 5, 10, 10)
                bullets.append(bullet)

    # Player movement
    keys = pygame.key.get_pressed()
    if keys[pygame.K_UP] and player_y > 0:
        player_y -= player_speed
    if keys[pygame.K_DOWN] and player_y < HEIGHT - player_height:
        player_y += player_speed

    player = pygame.Rect(player_x, player_y, player_width, player_height)
    pygame.draw.rect(screen, BLACK, player)

    # Bullets movement
    for bullet in bullets[:]:
        bullet.x += bullet_speed
        if bullet.x > WIDTH:
            bullets.remove(bullet)
        else:
            pygame.draw.rect(screen, RED, bullet)

    # Enemy movement
    for enemy in enemies[:]:
        enemy.x -= 3
        if enemy.x < 0:
            enemies.remove(enemy)
        else:
            pygame.draw.rect(screen, (0, 0, 255), enemy)

    # Collision detection
    for bullet in bullets[:]:
        for enemy in enemies[:]:
            if bullet.colliderect(enemy):
                bullets.remove(bullet)
                enemies.remove(enemy)
                break

    pygame.display.flip()

pygame.quit()
sys.exit()
